package com.eakurnikov.kaspressosample.domain.repository

import com.eakurnikov.kaspressosample.data.model.Resource
import com.eakurnikov.kaspressosample.data.db.dao.PostsDao
import com.eakurnikov.kaspressosample.data.model.Post
import com.eakurnikov.kaspressosample.data.db.entity.CommentEntity
import com.eakurnikov.kaspressosample.data.db.entity.PostEntity
import com.eakurnikov.kaspressosample.data.network.api.PostsApi
import com.eakurnikov.kaspressosample.data.network.dto.CommentDto
import com.eakurnikov.kaspressosample.data.network.dto.PostDto
import com.eakurnikov.kaspressosample.data.repository.PostsRepo
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Created by eakurnikov on 2019-12-15
 */
class PostsRepoImpl @Inject constructor(
    private val api: PostsApi,
    private val dao: PostsDao
) : PostsRepo {

    private var apiDisposable: Disposable? = null
    private var daoDisposable: Disposable? = null

    override val postsSubject: BehaviorSubject<Resource<List<Post>>> = BehaviorSubject.create()

    override fun getCachedPosts(amount: Int) {
        postsSubject.onNext(Resource.Loading())
        disposeDao()
        daoDisposable = dao.getPosts(amount)
            .flatMapPublisher { Flowable.fromIterable(it) }
            .flatMapSingle { postEntity: PostEntity ->
                Single.zip(
                    Single.just(postEntity),
                    dao.getCommentsForPost(postEntity.id),
                    BiFunction { post: PostEntity, comments: List<CommentEntity> ->
                        Post(post, comments)
                    }
                )
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { posts: List<Post> ->
                    postsSubject.onNext(Resource.Success(posts))
                },
                { error: Throwable ->
                    postsSubject.onNext(
                        Resource.Error(error.message ?: "Logical error in db")
                    )
                    disposeDao()
                }
            )
    }

    override fun loadPosts() {
        postsSubject.onNext(Resource.Loading())
        disposeApi()
        apiDisposable = api.getPosts()
            .flatMapPublisher { postDtos: List<PostDto> ->
                dao.insertPostsList(postDtos.map { PostEntity(it) })
                Flowable.fromIterable(postDtos)
            }
            .flatMapSingle { postDto: PostDto ->
                Single.zip(
                    Single.just(postDto),
                    api.getCommentsForPost(postDto.id),
                    BiFunction { post: PostDto, comments: List<CommentDto> ->
                        dao.insertCommentsList(comments.map { CommentEntity(it) })
                        Post(post, comments)
                    }
                )
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { posts: List<Post> ->
                    postsSubject.onNext(
                        Resource.Success(posts).apply {
                            (data as MutableList).add(
                                data.lastIndex + 1,
                                Post(
                                    0,
                                    0,
                                    "Last post",
                                    "Perfect body",
                                    emptyList()
                                )
                            )
                        }
                    )
                },
                { error: Throwable ->
                    postsSubject.onNext(
                        Resource.Error(error.message ?: "Logical error in db")
                    )
                    disposeDao()
                }
            )
    }

    private fun disposeApi() {
        apiDisposable?.dispose()
        apiDisposable = null
    }

    private fun disposeDao() {
        daoDisposable?.dispose()
        daoDisposable = null
    }
}
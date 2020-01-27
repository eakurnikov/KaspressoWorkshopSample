package com.eakurnikov.kaspressosample.viewmodel.posts

import com.eakurnikov.kaspressosample.data.model.Resource
import com.eakurnikov.kaspressosample.data.model.Post
import com.eakurnikov.kaspressosample.data.repository.PostsRepo
import com.eakurnikov.kaspressosample.viewmodel.base.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Created by eakurnikov on 2019-12-15
 */
class PostsViewModel @Inject constructor(
    private val postsRepo: PostsRepo
) : BaseViewModel() {

    private val cachedPostsAmount: Int = 2

    var postsSubject: BehaviorSubject<Resource<List<Post>>> =
        BehaviorSubject.createDefault(Resource.Loading(listOf()))

    override fun onStart() {
        super.onStart()
        postsRepo.getCachedPosts(cachedPostsAmount)
    }

    override fun subscribe() {
        subscribe(
            postsRepo.postsSubject.subscribe(
                { resource: Resource<List<Post>> ->
                    postsSubject.onNext(resource)
                },
                { error: Throwable ->
                    postsSubject.onNext(
                        Resource.Error(error.message ?: "Logical error in db")
                    )
                }
            )
        )
    }

    fun onRefresh(): Unit = postsRepo.loadPosts()
}
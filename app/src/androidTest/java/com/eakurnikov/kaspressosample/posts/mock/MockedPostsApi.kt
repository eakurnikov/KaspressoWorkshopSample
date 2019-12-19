package com.eakurnikov.kaspressosample.posts.mock

import com.eakurnikov.kaspressosample.data.network.api.PostsApi
import com.eakurnikov.kaspressosample.data.network.dto.*
import io.reactivex.Single

/**
 * Created by eakurnikov on 2019-12-18
 */
class MockedPostsApi : PostsApi {

    override fun getUsers(): Single<List<UserDto>> = Single.fromCallable {
        listOf(
            UserDto(
                1,
                "Test user #1",
                "Test username #1",
                "test@email.com",
                AddressDto(
                    "Test street #1",
                    "Test suite #1",
                    "Test city #1",
                    "Test zipcode #1",
                    GeoDto(
                        "Test lat #1",
                        "Test lng #1"
                    )
                ),
                "Test phone #1",
                "Test website #1",
                CompanyDto(
                    "Test company #1",
                    "Test catch phrase #1",
                    "Test bs #1"
                )
            )
        )
    }

    override fun getPosts(): Single<List<PostDto>> = Single.fromCallable {
        listOf(
            PostDto(1, 1, "Test post #1", "Test post body #1"),
            PostDto(2, 1, "Test post #2", "Test post body #2"),
            PostDto(3, 1, "Test post #3", "Test post body #3"),
            PostDto(4, 1, "Test post #4", "Test post body #4"),
            PostDto(5, 1, "Test post #5", "Test post body #5"),
            PostDto(6, 1, "Test post #6", "Test post body #6"),
            PostDto(7, 1, "Test post #7", "Test post body #7")
        )
    }

    override fun getComments(): Single<List<CommentDto>> = Single.fromCallable {
        listOf(
            CommentDto(
                1,
                1,
                "Test comment #1",
                "test@email.com",
                "Test comment body #1"
            ),
            CommentDto(
                2,
                1,
                "Test comment #2",
                "test@email.com",
                "Test comment body #2"
            ),
            CommentDto(
                3,
                2,
                "Test comment #3",
                "test@email.com",
                "Test comment body #3"
            )
        )
    }

    override fun getCommentsForPost(postId: Long): Single<List<CommentDto>>? = Single.fromCallable {
        listOf(
            CommentDto(
                1,
                postId,
                "Test comment #1 for post #$postId",
                "test@email.com",
                "Test comment body #1"
            ),
            CommentDto(
                2,
                postId,
                "Test comment #2 for post #$postId",
                "test@email.com",
                "Test comment body #2"
            ),
            CommentDto(
                3,
                postId,
                "Test comment #3 for post #$postId",
                "test@email.com",
                "Test comment body #3"
            )
        )
    }
}
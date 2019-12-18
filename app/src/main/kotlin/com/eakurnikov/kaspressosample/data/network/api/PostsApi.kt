package com.eakurnikov.kaspressosample.data.network.api

import com.eakurnikov.kaspressosample.data.network.dto.CommentDto
import com.eakurnikov.kaspressosample.data.network.dto.PostDto
import com.eakurnikov.kaspressosample.data.network.dto.UserDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by eakurnikov on 2019-12-15
 */
interface PostsApi {

    @GET("/users")
    fun getUsers(): Single<List<UserDto>>

    @GET("/posts")
    fun getPosts(): Single<List<PostDto>>

    @GET("/comments")
    fun getComments(): Single<List<CommentDto>>

    @GET("/comments")
    fun getCommentsForPost(@Query("postId") postId: Long): Single<List<CommentDto>>?
}
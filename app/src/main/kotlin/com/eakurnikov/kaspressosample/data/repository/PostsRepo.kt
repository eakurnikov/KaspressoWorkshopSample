package com.eakurnikov.kaspressosample.data.repository

import com.eakurnikov.kaspressosample.data.model.Resource
import com.eakurnikov.kaspressosample.data.model.Post
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by eakurnikov on 2019-12-15
 */
interface PostsRepo {

    val postsSubject: BehaviorSubject<Resource<List<Post>>>

    fun getCachedPosts(amount: Int)

    fun loadPosts()
}
package com.eakurnikov.kaspressosample.data.repository

import com.eakurnikov.kaspressosample.data.model.Resource
import com.eakurnikov.kaspressosample.data.model.Post
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by eakurnikov on 2019-10-05
 */
interface PostsRepo {

    val postsSubject: BehaviorSubject<Resource<List<Post>>>

    fun getCachedPosts()

    fun loadPosts()
}
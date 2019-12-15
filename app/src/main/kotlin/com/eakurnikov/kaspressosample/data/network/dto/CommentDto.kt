package com.eakurnikov.kaspressosample.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by eakurnikov on 2019-12-15
 */
data class CommentDto(

    @SerializedName("id")
    val id: Long,

    @SerializedName("postId")
    val postId: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("body")
    val body: String
)
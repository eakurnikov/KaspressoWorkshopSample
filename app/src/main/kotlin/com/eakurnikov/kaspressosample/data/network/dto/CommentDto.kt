package com.eakurnikov.kaspressosample.data.network.dto

import com.google.gson.annotations.SerializedName

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
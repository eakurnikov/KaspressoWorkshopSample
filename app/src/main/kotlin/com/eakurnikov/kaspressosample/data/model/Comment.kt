package com.eakurnikov.kaspressosample.data.model

import com.eakurnikov.kaspressosample.data.db.entity.CommentEntity
import com.eakurnikov.kaspressosample.data.network.dto.CommentDto

/**
 * Created by eakurnikov on 2019-12-15
 */
data class Comment(
    val commentId: Long,
    val postId: Long,
    val name: String,
    val email: String,
    val body: String
) {
    constructor(
        commentDto: CommentDto
    ) : this(
        commentDto.id,
        commentDto.postId,
        commentDto.name,
        commentDto.email,
        commentDto.body
    )

    constructor(
        commentEntity: CommentEntity
    ) : this(
        commentEntity.id,
        commentEntity.postId,
        commentEntity.name,
        commentEntity.email,
        commentEntity.body
    )
}
package com.eakurnikov.kaspressosample.data.model

import com.eakurnikov.kaspressosample.data.db.entity.CommentEntity
import com.eakurnikov.kaspressosample.data.db.entity.PostEntity
import com.eakurnikov.kaspressosample.data.network.dto.CommentDto
import com.eakurnikov.kaspressosample.data.network.dto.PostDto

/**
 * Created by eakurnikov on 2019-12-15
 */
data class Post(
    val postId: Long,
    val userId: Long,
    val title: String,
    val body: String,
    val comments: List<Comment>
) {
    constructor(
        postDto: PostDto,
        commentDtos: List<CommentDto>
    ) : this(
        postDto.id,
        postDto.userId,
        postDto.title,
        postDto.body,
        commentDtos.map { Comment(it) }
    )

    constructor(
        postEntity: PostEntity,
        commentEntities: List<CommentEntity>
    ) : this(
        postEntity.id,
        postEntity.userId,
        postEntity.title,
        postEntity.body,
        commentEntities.map { Comment(it) }
    )
}
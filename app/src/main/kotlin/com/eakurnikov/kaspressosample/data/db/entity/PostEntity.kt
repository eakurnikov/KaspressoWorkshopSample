package com.eakurnikov.kaspressosample.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.eakurnikov.kaspressosample.data.db.DatabaseNames.POSTS_TABLE_NAME
import com.eakurnikov.kaspressosample.data.network.dto.PostDto

/**
 * Created by eakurnikov on 2019-12-15
 */
@Entity(
    tableName = POSTS_TABLE_NAME,
    indices = [Index("id", unique = true)]
)
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val dbId: Long?,
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
) {
    constructor(
        postDto: PostDto
    ) : this(
        null,
        postDto.id,
        postDto.userId,
        postDto.title,
        postDto.body
    )
}
package com.eakurnikov.kaspressosample.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.eakurnikov.kaspressosample.data.db.DatabaseNames.COMMENTS_TABLE_NAME
import com.eakurnikov.kaspressosample.data.network.dto.CommentDto

/**
 * Created by eakurnikov on 2019-12-15
 */
@Entity(
    tableName = COMMENTS_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = PostEntity::class,
            parentColumns = ["id"],
            childColumns = ["postId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("id", unique = true)]
)
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val dbId: Long?,
    val id: Long,
    val postId: Long,
    val name: String,
    val email: String,
    val body: String
) {
    constructor(
        commentDto: CommentDto
    ) : this(
        null,
        commentDto.id,
        commentDto.postId,
        commentDto.name,
        commentDto.email,
        commentDto.body
    )
}
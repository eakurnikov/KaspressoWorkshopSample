package com.eakurnikov.kaspressosample.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eakurnikov.kaspressosample.data.db.DatabaseNames.COMMENTS_TABLE_NAME
import com.eakurnikov.kaspressosample.data.db.DatabaseNames.POSTS_TABLE_NAME
import com.eakurnikov.kaspressosample.data.db.entity.PostEntity
import com.eakurnikov.kaspressosample.data.db.entity.CommentEntity
import io.reactivex.Single

/**
 * Created by eakurnikov on 2019-12-15
 */
@Dao
interface PostsDao {

    @Query("SELECT * FROM $POSTS_TABLE_NAME LIMIT :amount")
    fun getPosts(amount: Int): Single<List<PostEntity>>

    @Query("SELECT * FROM $COMMENTS_TABLE_NAME WHERE postId = :postEntityId")
    fun getCommentsForPost(postEntityId: Long): Single<List<CommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPostsList(posts: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommentsList(comments: List<CommentEntity>)
}
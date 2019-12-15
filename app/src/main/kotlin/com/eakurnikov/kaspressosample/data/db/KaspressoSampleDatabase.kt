package com.eakurnikov.kaspressosample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eakurnikov.kaspressosample.data.db.dao.PostsDao
import com.eakurnikov.kaspressosample.data.db.entity.PostEntity
import com.eakurnikov.kaspressosample.data.db.entity.CommentEntity

/**
 * Created by eakurnikov on 2019-12-15
 */
@Database(
    entities = [
        PostEntity::class,
        CommentEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class KaspressoSampleDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao
}
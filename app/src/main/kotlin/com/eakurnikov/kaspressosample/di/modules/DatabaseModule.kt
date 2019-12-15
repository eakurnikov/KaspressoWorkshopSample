package com.eakurnikov.kaspressosample.di.modules

import android.content.Context
import androidx.room.Room
import com.eakurnikov.common.annotations.AppContext
import com.eakurnikov.common.di.annotations.AppScope
import com.eakurnikov.kaspressosample.data.db.DatabaseNames.DATABASE_NAME
import com.eakurnikov.kaspressosample.data.db.KaspressoSampleDatabase
import dagger.Module
import dagger.Provides

/**
 * Created by eakurnikov on 2019-12-15
 */
@Module
class DatabaseModule {

    @Provides
    @AppScope
    fun provideKaspressoSampleDatabase(@AppContext context: Context): KaspressoSampleDatabase {
        return Room
            .databaseBuilder(context, KaspressoSampleDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}
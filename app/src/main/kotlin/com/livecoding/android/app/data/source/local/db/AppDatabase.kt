package com.livecoding.android.app.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.livecoding.android.app.data.source.local.db.dao.NowPlayingDao
import com.livecoding.android.app.data.source.local.db.entity.NowPlayingEntity

@Database(entities = [NowPlayingEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nowPlayingDao(): NowPlayingDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        private const val DB_NAME = "movies.db"

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, DB_NAME
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }
}
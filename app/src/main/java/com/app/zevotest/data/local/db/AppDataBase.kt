package com.app.zevotest.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.zevotest.data.local.dao.NewsDao
import com.app.zevotest.data.remote.models.Article


@Database(entities = [Article::class], version = 4, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): AppDataBase {
         return   Room.databaseBuilder(context, AppDataBase::class.java, "app_name_zevo")
                .fallbackToDestructiveMigration().build()
        }
    }

    abstract fun getNewsFao(): NewsDao

}
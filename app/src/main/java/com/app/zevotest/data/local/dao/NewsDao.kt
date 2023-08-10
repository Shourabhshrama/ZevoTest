package com.app.zevotest.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.zevotest.data.remote.models.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLit(list: List<Article>)


    @Query("SELECT * FROM Article")
    fun getArticle():  Flow<List<Article>>

}
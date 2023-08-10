package com.app.zevotest.data.remote.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Article")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,

    val title: String,
    val url: String,
    val urlToImage: String
)
package com.app.zevotest.di


import android.content.Context
import com.app.zevotest.BASE_URL
import com.app.zevotest.BuildConfig
import com.app.zevotest.data.local.dao.NewsDao
import com.app.zevotest.data.local.db.AppDataBase

import com.app.zevotest.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpclient(): OkHttpClient {
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel((HttpLoggingInterceptor.Level.BASIC))
            return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .build()
        } else {
            return OkHttpClient.Builder().build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build().create()
    }


    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideNewDao(appDataBase: AppDataBase): NewsDao {
        return appDataBase.getNewsFao()
    }


}
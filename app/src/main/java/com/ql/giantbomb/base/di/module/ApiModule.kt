package com.ql.giantbomb.base.di.module

import com.ql.giantbomb.base.api.GamesService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 *   Created by OLABODE WILSON on 8/24/20.
 */

@Module
object ApiModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideOkHttp(): Call.Factory {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
        callFactory: Call.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.giantbomb.com/")
            .callFactory(callFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideGameService(retrofit: Retrofit): GamesService {
        return retrofit.create()
    }
}


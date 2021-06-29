package com.yarmouk.photogalleryapp.di

import com.yarmouk.photogalleryapp.api.UnSplashApi
import com.yarmouk.photogalleryapp.others.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //this will provide an instance of retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    //this will provide instance of our api
    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnSplashApi =
        retrofit.create(UnSplashApi::class.java)

}
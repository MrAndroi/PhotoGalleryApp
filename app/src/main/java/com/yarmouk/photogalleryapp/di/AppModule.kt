package com.yarmouk.photogalleryapp.di

import android.content.Context
import androidx.room.Room
import com.yarmouk.photogalleryapp.api.UnSplashApi
import com.yarmouk.photogalleryapp.db.ImagesDatabase
import com.yarmouk.photogalleryapp.others.Constants.BASE_URL
import com.yarmouk.photogalleryapp.others.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    //this function will provide instance of our database whenever we need it
    @Singleton
    @Provides
    fun provideImagesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ImagesDatabase::class.java,DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    //this function will provide instance of our Dao whenever we need it
    @Provides
    @Singleton
    fun provideImagesDoa(imagesDatabase: ImagesDatabase) =
        imagesDatabase.getImagesDao()

}
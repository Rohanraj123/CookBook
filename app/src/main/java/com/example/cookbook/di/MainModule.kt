package com.example.cookbook.di

import com.example.cookbook.data.datasource.api.RetrofitApi
import com.example.cookbook.data.reposiitory.RandomRecipeRepository
import com.example.cookbook.data.reposiitory.RandomRecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.spoonacular.com/"

@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitApi(retrofit: Retrofit): RetrofitApi {
        return retrofit.create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRandomRecipeRepository(retrofitAPI: RetrofitApi): RandomRecipeRepository {
        return RandomRecipeRepositoryImpl(retrofitAPI)
    }
}
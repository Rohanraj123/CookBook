package com.example.cookbook.di

import com.example.cookbook.data.datasource.api.RetrofitApi
import com.example.cookbook.data.reposiitory.GetRecipeInformationRepository
import com.example.cookbook.data.reposiitory.GetRecipeInformationRepositoryImpl
import com.example.cookbook.data.reposiitory.RandomRecipeRepository
import com.example.cookbook.data.reposiitory.RandomRecipeRepositoryImpl
import com.example.cookbook.data.reposiitory.SearchRecipeRepository
import com.example.cookbook.data.reposiitory.SearchRecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.spoonacular.com/recipes/"

@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return try {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } catch (e: IllegalArgumentException) {
            throw e
        }
    }

    @Provides
    @Singleton
    fun providesRetrofitApi(retrofit: Retrofit): RetrofitApi {
        return try {
            retrofit.create(RetrofitApi::class.java)
        } catch (e: IllegalArgumentException) {
            throw e
        }
    }

    @Provides
    @Singleton
    fun providesRandomRecipeRepository(retrofitAPI: RetrofitApi): RandomRecipeRepository {
        return RandomRecipeRepositoryImpl(retrofitAPI)
    }

    @Provides
    @Singleton
    fun providesSearchRecipeRepository(retrofitAPI: RetrofitApi): SearchRecipeRepository {
        return SearchRecipeRepositoryImpl(retrofitAPI)
    }

    @Provides
    @Singleton
    fun providesGetRecipeInformationRepository(retrofitAPI: RetrofitApi): GetRecipeInformationRepository {
        return GetRecipeInformationRepositoryImpl(retrofitAPI)
    }
}
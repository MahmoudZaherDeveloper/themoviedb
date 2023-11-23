package com.zaher.cleanwithmvvm.di.moviesmodule

import com.squareup.moshi.Moshi
import com.zaher.cleanwithmvvm.data.source.remote.MovieApiService
import com.zaher.cleanwithmvvm.data.source.remote.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Module
@InstallIn(SingletonComponent::class)
class MoviesNetworkModule {

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder().addInterceptor(RequestInterceptor()).addInterceptor(interceptor)
            .build()


    @Singleton
    @Provides
    fun provideRetrofitService(okHttpClient: OkHttpClient): MovieApiService =
        Retrofit.Builder()
            .baseUrl(MovieApiService.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(okHttpClient)
            .build()
            .create(MovieApiService::class.java)

}
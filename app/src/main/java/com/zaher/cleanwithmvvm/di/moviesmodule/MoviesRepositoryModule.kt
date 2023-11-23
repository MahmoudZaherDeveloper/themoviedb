package com.zaher.cleanwithmvvm.di.moviesmodule

import com.zaher.cleanwithmvvm.data.repository.MovieRepositoryImpl
import com.zaher.cleanwithmvvm.data.source.local.roomdb.dao.MovieDao
import com.zaher.cleanwithmvvm.data.source.local.roomdb.dao.PopularMoviesDao
import com.zaher.cleanwithmvvm.data.source.remote.MovieApiService
import com.zaher.cleanwithmvvm.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepositoryImpl(
        movieApiService: MovieApiService,
        popularMoviesDao: PopularMoviesDao,
        movieDao: MovieDao
    ): MovieRepository = MovieRepositoryImpl(movieApiService, popularMoviesDao, movieDao)

}
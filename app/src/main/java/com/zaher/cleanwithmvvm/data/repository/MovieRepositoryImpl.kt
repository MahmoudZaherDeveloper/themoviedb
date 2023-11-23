package com.zaher.cleanwithmvvm.data.repository

import com.zaher.cleanwithmvvm.data.source.local.roomdb.dao.MovieDao
import com.zaher.cleanwithmvvm.data.source.local.roomdb.dao.PopularMoviesDao
import com.zaher.cleanwithmvvm.data.source.remote.MovieApiService
import com.zaher.cleanwithmvvm.data.source.remote.Resource
import com.zaher.cleanwithmvvm.domain.model.Movie
import com.zaher.cleanwithmvvm.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val popularMoviesDao: PopularMoviesDao,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            fetchAndInsertPopularMovies(movieApiService, popularMoviesDao, movieDao)
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
        // single source of truth we will emit data from db only and not directly from remote
        emit(Resource.Success(getPopularMoviesFromDb(movieDao)))
    }


    private suspend fun fetchAndInsertPopularMovies(
        movieApiService: MovieApiService,
        popularMoviesDao: PopularMoviesDao,
        movieDao: MovieDao
    ) {

        val remotePopularMovies = movieApiService.getPopularMovies()
        popularMoviesDao.insertPopularMovies(remotePopularMovies.toPopularMoviesEntity())
        movieDao.insertMovieList(remotePopularMovies.results.map { it.toMovieEntity() }) //now insert newly fetched data to db

    }


    private suspend fun getPopularMoviesFromDb(movieDao: MovieDao): List<Movie> {
        val newPopularMovies = movieDao.getMovieList().map { it.toMovie() }
        return newPopularMovies
    }


}
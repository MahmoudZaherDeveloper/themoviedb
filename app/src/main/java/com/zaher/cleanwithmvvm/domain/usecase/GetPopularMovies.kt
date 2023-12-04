package com.zaher.cleanwithmvvm.domain.usecase


import com.zaher.cleanwithmvvm.data.source.remote.Resource
import com.zaher.cleanwithmvvm.domain.model.Movie
import com.zaher.cleanwithmvvm.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMovies @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }
}
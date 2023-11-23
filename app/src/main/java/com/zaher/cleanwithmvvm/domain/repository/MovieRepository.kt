package com.zaher.cleanwithmvvm.domain.repository


import com.zaher.cleanwithmvvm.data.source.remote.Resource
import com.zaher.cleanwithmvvm.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
}
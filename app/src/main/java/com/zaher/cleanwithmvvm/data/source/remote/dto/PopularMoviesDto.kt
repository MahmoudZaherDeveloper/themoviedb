package com.zaher.cleanwithmvvm.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zaher.cleanwithmvvm.data.source.local.roomdb.entity.PopularMoviesEntity

@JsonClass(generateAdapter = true)
data class PopularMoviesDto(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieDto>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
) {
    fun toPopularMoviesEntity(): PopularMoviesEntity {
        return PopularMoviesEntity(
            page = page,
            results = results.map { it.toMovieEntity() }
        )
    }
}
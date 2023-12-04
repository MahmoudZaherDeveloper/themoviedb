package com.zaher.cleanwithmvvm.data.source.local.roomdb.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zaher.cleanwithmvvm.domain.model.Movie

@Entity(tableName = MovieEntity.TABLE_NAME)
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    @ColumnInfo(name = "poster_url") val posterUrl: String,
    @ColumnInfo(name = "release_date") val releaseDate: String
) {

    fun toMovie(): Movie {
        return Movie(
            title = title,
            overview = overview,
            posterUrl = posterUrl,
            releaseDate = releaseDate
        )
    }

    companion object {
        const val TABLE_NAME = "movie"
    }
}
package com.zaher.cleanwithmvvm.data.source.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zaher.cleanwithmvvm.data.source.local.roomdb.entity.MovieEntity

@Dao
interface PopularMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movies: List<MovieEntity>)

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME}")
    suspend fun getMovieList(): List<MovieEntity>

    @Query("DELETE FROM ${MovieEntity.TABLE_NAME}")
    suspend fun deleteAll()
}
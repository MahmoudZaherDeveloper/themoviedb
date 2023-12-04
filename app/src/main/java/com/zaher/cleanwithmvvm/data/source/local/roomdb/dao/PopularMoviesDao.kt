package com.zaher.cleanwithmvvm.data.source.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zaher.cleanwithmvvm.data.source.local.roomdb.entity.MovieEntity
import com.zaher.cleanwithmvvm.data.source.local.roomdb.entity.PopularMoviesEntity

@Dao
interface PopularMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movies: PopularMoviesEntity)

    @Query("SELECT * FROM ${PopularMoviesEntity.TABLE_NAME}")
    suspend fun getPopularMovies(): PopularMoviesEntity

    @Query("DELETE FROM ${MovieEntity.TABLE_NAME}")
    suspend fun deleteAll()
}
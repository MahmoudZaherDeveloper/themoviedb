package com.zaher.cleanwithmvvm.data.source.local.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zaher.cleanwithmvvm.data.source.local.roomdb.dao.MovieDao
import com.zaher.cleanwithmvvm.data.source.local.roomdb.dao.PopularMoviesDao
import com.zaher.cleanwithmvvm.data.source.local.roomdb.entity.MovieEntity
import com.zaher.cleanwithmvvm.data.source.local.roomdb.entity.PopularMoviesEntity

@Database(
    entities = [PopularMoviesEntity::class, MovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(PopularMoviesEntityConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao

    abstract fun getPopularMoviesDao(): PopularMoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
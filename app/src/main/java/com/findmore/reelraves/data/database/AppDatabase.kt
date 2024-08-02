package com.findmore.reelraves.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.findmore.reelraves.data.dao.GenreDao
import com.findmore.reelraves.data.dao.MovieDao
import com.findmore.reelraves.data.dao.TvSeriesDao
import com.findmore.reelraves.data.model.Genre
import com.findmore.reelraves.data.model.Movie
import com.findmore.reelraves.data.model.TvSeries


@Database(entities = [Movie::class, TvSeries::class, Genre::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao
    abstract fun tvSeriesDao(): TvSeriesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
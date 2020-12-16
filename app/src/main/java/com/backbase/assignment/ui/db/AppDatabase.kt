package com.backbase.assignment.ui.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.backbase.assignment.ui.db.converters.Converters
import com.backbase.assignment.ui.db.daos.MovieDetailDao
import com.backbase.assignment.ui.models.moviedetail.*
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "moviedetails").build()
    }

    fun provideMovieDetailDao(database: AppDatabase): MovieDetailDao {
        return database.movieDetailDao()
    }

    factory { provideMovieDetailDao(get()) }
    single { provideDatabase(get()) }
}

@Database(
    entities = [MovieDetailModel::class, Genre::class, ProductionCompany::class, ProductionCountry::class, SpokenLanguage::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDetailDao(): MovieDetailDao
}

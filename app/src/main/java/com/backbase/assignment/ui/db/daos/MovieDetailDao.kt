package com.backbase.assignment.ui.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.backbase.assignment.ui.models.moviedetail.MovieDetailModel

@Dao
interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetailModel: MovieDetailModel): Long

    @Query("select * from movie_detail_table where id=:id")
    suspend fun getMovieDetail(id: Int): MovieDetailModel

    @Query("delete from movie_detail_table")
    suspend fun deleteAllMovie()
}

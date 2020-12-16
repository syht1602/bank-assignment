package com.backbase.assignment.ui.models.moviedetail

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie_detail_table")
@Parcelize
data class MovieDetailModel(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String,
    @ColumnInfo(name = "belongs_to_collection")
    val belongs_to_collection: BelongsToCollection,
    @ColumnInfo(name = "budget")
    val budget: Int,
    @ColumnInfo(name = "genres")
    val genres: List<Genre>,
    @ColumnInfo(name = "homepage")
    val homepage: String,
    @ColumnInfo(name = "imdb_id")
    val imdb_id: String,
    @ColumnInfo(name = "original_language")
    val original_language: String,
    @ColumnInfo(name = "original_title")
    val original_title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "production_companies")
    val production_companies: List<ProductionCompany>,
    @ColumnInfo(name = "production_countries")
    val production_countries: List<ProductionCountry>,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "revenue")
    val revenue: Int,
    @ColumnInfo(name = "runtime")
    val runtime: Float,
    @ColumnInfo(name = "spoken_languages")
    val spoken_languages: List<SpokenLanguage>,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "tagline")
    val tagline: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int
) : Parcelable

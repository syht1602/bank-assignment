package com.backbase.assignment.ui.services

import com.backbase.assignment.ui.models.moviedetail.MovieDetailModel
import com.backbase.assignment.ui.models.nowplaying.NowPlayingResponse
import com.backbase.assignment.ui.models.popular.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun fetchNowPlayingList(
        @Query("language") language: String,
        @Query("page") page: String,
        @Query("api_key") apiKey: String
    ): Response<NowPlayingResponse>

    @GET("movie/popular")
    suspend fun fetchPopularList(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<PopularResponse>


    @GET("movie/{MOVIE_ID}")
    suspend fun fetchMovieDetail(
        @Path("MOVIE_ID") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailModel>
}

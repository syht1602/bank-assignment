package com.backbase.assignment.ui.repositories

import android.content.Context
import com.backbase.assignment.ui.models.moviedetail.MovieDetailModel
import com.backbase.assignment.ui.models.nowplaying.NowPlayingResponse
import com.backbase.assignment.ui.models.popular.PopularResponse
import com.backbase.assignment.ui.services.MovieService
import org.koin.dsl.module
import retrofit2.Response

val movieModule = module {
    factory { MovieRepository(get(), get()) }
}

class MovieRepository(
    val context: Context,
    private val movieService: MovieService
) {

    /**
     * Fetching now playing from Api
     *
     * @param language
     * @param page
     * @param apiKey
     */
    suspend fun fetchNowPlayingList(
        language: String = "en-US",
        page: String = "undefined",
        apiKey: String
    ): Response<NowPlayingResponse> {
        return movieService.fetchNowPlayingList(language = language, page = page, apiKey = apiKey)
    }

    /**
     * Fetching popular list from Api by pagination
     *
     * @param language
     * @param page
     * @param apiKey
     */
    suspend fun fetchPopularList(
        language: String = "en-US",
        page: Int,
        apiKey: String
    ): Response<PopularResponse> {
        return movieService.fetchPopularList(language = language, page = page, apiKey = apiKey)
    }

    /**
     * Fetching movie detail from Api
     * Check if is clicked on item to get detail or call Api for storing value
     *
     * @param movieId
     * @param apiKey
     */
    suspend fun fetchMovieDetail(
        movieId: Int,
        apiKey: String
    ): Response<MovieDetailModel> {
        return movieService.fetchMovieDetail(movieId, apiKey = apiKey)
    }
}

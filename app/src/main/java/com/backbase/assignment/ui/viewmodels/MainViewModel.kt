package com.backbase.assignment.ui.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.ui.bases.BaseViewModel
import com.backbase.assignment.ui.models.moviedetail.MovieDetailModel
import com.backbase.assignment.ui.repositories.MovieRepository
import com.backbase.assignment.ui.utils.Constants.Companion.API_KEY
import com.backbase.assignment.ui.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.net.InetAddress

class MainViewModel(private val repo: MovieRepository) : BaseViewModel() {
    private var currentPage = MutableLiveData<Int>()
    val dialogState = MutableLiveData<Boolean>()
    val movieDetail = MutableLiveData<MovieDetailModel>()

    init {
        dialogState.value = false
        currentPage.value = 1
    }

    val nowPlayingList = liveData(Dispatchers.IO) {
        try {
            val response = repo.fetchNowPlayingList(apiKey = API_KEY)
            if (response.isSuccessful) {
                Log.d("Response", response.body().toString())
                emit(response.body()!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("MainViewModel", "Cannot get Now Playing List")
        }
    }

    fun getMovieDetail(movieId: Int) {
        try {
            viewModelScope.launch {
                val waitForData = async {
                    val response = repo.fetchMovieDetail(movieId, API_KEY)
                    if (response.isSuccessful) {
                        movieDetail.value = response.body()
                    }
                }
                waitForData.await()
                changeDialogState()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("MainViewModel", "Cannot get Movie Detail")
        }
    }

    /**
     * Trigger when load more
     * Increase page after each load
     */
    fun onLoadMore() {
        currentPage.value = currentPage.value!!.plus(1)
    }

    /**
     * Store hide and show dialog sate
     */
    fun changeDialogState() {
        dialogState.value = !dialogState.value!!
    }

    /**
     * Return popular list
     * Call everytime current page change (for pagination)
     * Get runtime by call Movie detail API
     */
    var popularList = Transformations.switchMap(currentPage) { page ->
        liveData(Dispatchers.IO) {
            try {
                val response = repo.fetchPopularList(apiKey = API_KEY, page = page)
                if (response.isSuccessful) {
                    Log.d("Response", response.body().toString())
                    val popularList = response.body()!!

                    //Block thread when call this to apply runtime to current item
                    coroutineScope {
                        popularList.results.map {
                            launch {
                                try {
                                    val movieDetailResponse =
                                        repo.fetchMovieDetail(it.id, apiKey = API_KEY)
                                    if (movieDetailResponse.isSuccessful) {
                                        Log.d("Response", response.body().toString())
                                        it.runtime =
                                            Utils.runtimeConverter(movieDetailResponse.body()!!.runtime)
                                    }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }

                    }
                    emit(popularList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("MainViewModel", "Cannot get Popular List")
            }
        }
    }
}

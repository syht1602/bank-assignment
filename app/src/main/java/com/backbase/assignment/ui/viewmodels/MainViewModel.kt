package com.backbase.assignment.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.ui.models.moviedetail.MovieDetailModel
import com.backbase.assignment.ui.models.popular.PopularModel
import com.backbase.assignment.ui.repositories.MovieRepository
import com.backbase.assignment.ui.utils.Constants.Companion.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MovieRepository) : ViewModel() {
    private var currentPage = MutableLiveData<Int>()
    val dialogState = MutableLiveData<Boolean>()
    val movieDetail = MutableLiveData<MovieDetailModel>()


    val _popularList = MutableLiveData<List<PopularModel>>()
    val popularList: LiveData<List<PopularModel>>
        get() = _popularList

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
    fun fetchPopularList() {
        viewModelScope.launch {
            try {
                val response = repo.fetchPopularList(
                    apiKey = API_KEY,
                    page = currentPage.value!!
                )
                if (response.isSuccessful) {
                    android.util.Log.d("Popular Response", response.body().toString())
                    _popularList.value = response.body()!!.results
                }
            } catch (e: Exception) {
                e.printStackTrace()
                android.util.Log.e("MainViewModel", "Cannot get Popular List")
            }
        }
    }
}

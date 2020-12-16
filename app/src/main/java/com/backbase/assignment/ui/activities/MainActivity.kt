package com.backbase.assignment.ui.activities

import android.app.Dialog
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ActivityMainBinding
import com.backbase.assignment.databinding.DialogMovieDetailBinding
import com.backbase.assignment.ui.adapters.NowPlayingAdapter
import com.backbase.assignment.ui.adapters.PopularMoviesAdapter
import com.backbase.assignment.ui.bases.BaseActivity
import com.backbase.assignment.ui.interfaces.IPopularAdapter
import com.backbase.assignment.ui.models.nowplaying.NowPlayingModel
import com.backbase.assignment.ui.models.popular.PopularModel
import com.backbase.assignment.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>(), CoroutineScope by MainScope() {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBinding: DialogMovieDetailBinding

    private var nowPlayingList = ArrayList<NowPlayingModel>()
    private lateinit var nowPlayingAdapter: NowPlayingAdapter

    private var popularList = ArrayList<PopularModel>()
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter

    private lateinit var movieDetailDialog: Dialog

    override fun getLayout(): Int {
        return R.layout.activity_main
    }


    override fun onCreateInit() {
        super.onCreateInit()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpNowPlayingRecyclerView()
        setPopularRecyclerView()
        setupMovieDetailObserve()
        setupMovieDetailDialogObserve()
    }

    /**
     * Set up Now playing adapter and recyclerview
     * Handle resource when received
     */
    private fun setUpNowPlayingRecyclerView() {
        nowPlayingAdapter = NowPlayingAdapter(nowPlayingList)
        rcvNowPlaying.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rcvNowPlaying.adapter = nowPlayingAdapter
        rcvNowPlaying.setItemViewCacheSize(20);
        try {
            viewModel.nowPlayingList.observe(this, Observer {
                it?.let {
                    val resultListNowPlayingModel: List<NowPlayingModel> = it.results
                    nowPlayingList.addAll(resultListNowPlayingModel)
                    rcvNowPlaying.adapter?.notifyDataSetChanged()
                }
            })
        } catch (e: Exception) {
            Toast.makeText(this, "Some error occur", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Set up Popular Movie adapter and recyclerview
     * Handle resource when received
     * Handle load more (if less than 3 items, will load more) by Interface
     * Handle item click by Interface
     */
    private fun setPopularRecyclerView() {
        val popularAdapter: IPopularAdapter = object : IPopularAdapter {
            override fun loadMore() {
                if (!isInternetAvailable()) {
                    return
                }
                viewModel.onLoadMore()
            }

            override fun movieItemClick(movieId: PopularModel) {
                if (!isInternetAvailable()) {
                    return
                }
                viewModel.getMovieDetail(movieId.id)
            }
        }

        popularMoviesAdapter = PopularMoviesAdapter(popularList, popularAdapter)
        rcvPopular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcvPopular.adapter = popularMoviesAdapter
        rcvPopular.setItemViewCacheSize(20);
        try {
            viewModel.popularList.observe(this, Observer {
                it?.let {
                    val resultListNowPlayingModel: List<PopularModel> = it.results
                    popularList.addAll(resultListNowPlayingModel)
                    rcvPopular.adapter?.notifyDataSetChanged()
                }
            })
        } catch (e: Exception) {
            Toast.makeText(this, "Some error occur", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupMovieDetailObserve() {
        try {
            viewModel.movieDetail.observe(this@MainActivity, Observer { movieDetail ->
                if (movieDetail != null) {
                    dialogBinding.movieDetail = movieDetail
                }
            })
        } catch (e: Exception) {
            Toast.makeText(this, "Some error occur", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Setup dialog to show the movie detail.
     */
    private fun setupMovieDetailDialogObserve() {
        dialogBinding = DialogMovieDetailBinding.inflate(LayoutInflater.from(this))
        movieDetailDialog = Dialog(this, R.style.DialogCustomTheme)
        movieDetailDialog.setContentView(dialogBinding.root)
        movieDetailDialog.setCancelable(false)
        dialogBinding.mainViewModel = viewModel
        try {
            viewModel.dialogState.observe(this, Observer {
                if (it) movieDetailDialog.show() else movieDetailDialog.dismiss()
            })
        } catch (e: Exception) {
            Toast.makeText(this, "Some error occur", Toast.LENGTH_SHORT).show()
        }
    }

}

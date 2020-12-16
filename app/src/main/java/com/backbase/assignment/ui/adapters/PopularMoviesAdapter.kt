package com.backbase.assignment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.databinding.MovieItemBinding
import com.backbase.assignment.ui.interfaces.IPopularAdapter
import com.backbase.assignment.ui.models.popular.PopularModel
import com.backbase.assignment.ui.utils.Constants
import com.backbase.assignment.ui.utils.Utils
import com.bumptech.glide.Glide

class PopularMoviesAdapter(
    private val popularItems: ArrayList<PopularModel>,
    private val popularInterface: IPopularAdapter
) :
    RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MovieItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.movie_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(popularItems[position], position, popularInterface, itemCount)

    override fun getItemCount(): Int = popularItems.size

    class ViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        //        lateinit var rating: RatingView
        fun bind(
            item: PopularModel,
            position: Int,
            popularInterface: IPopularAdapter,
            itemCount: Int
        ) = with(itemView) {
            Glide
                .with(context)
                .load(Utils.imageUrlGenerator(item.poster_path))
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_sharp_broken_image_24)
                .into(binding.poster)
            binding.title.text = item.title
            binding.releaseDate.text = Utils.dateConverter(item.release_date)
            binding.tvRuntime.text = item.runtime
            binding.clMovieItem.setOnClickListener {
                popularInterface.movieItemClick(item)
            }
            if (itemCount - position == Constants.ITEM_LEFT_FOR_LOAD_MORE) {
                popularInterface.loadMore()
            }
        }
    }
}

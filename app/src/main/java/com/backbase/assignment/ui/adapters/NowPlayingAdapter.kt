package com.backbase.assignment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ViewNowPlayingItemBinding
import com.backbase.assignment.ui.models.nowplaying.NowPlayingModel
import com.backbase.assignment.ui.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class NowPlayingAdapter(private val nowShowingItems: ArrayList<NowPlayingModel>) :
    RecyclerView.Adapter<NowPlayingAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewNowPlayingItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.view_now_playing_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(nowShowingItems[position])
    }

    override fun getItemCount(): Int = nowShowingItems.size

    class MyViewHolder(
        private val binding: ViewNowPlayingItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NowPlayingModel) = with(itemView) {
            Glide
                .with(context)
                .load(Utils.imageUrlGenerator(item.backdrop_path))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(binding.ivNowPlayingItem)
        }
    }
}

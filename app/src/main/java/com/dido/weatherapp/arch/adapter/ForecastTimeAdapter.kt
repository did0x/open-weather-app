package com.dido.weatherapp.arch.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.dido.weatherapp.BuildConfig
import com.dido.weatherapp.arch.base.BaseListAdapter
import com.dido.weatherapp.arch.base.BaseViewHolder
import com.dido.weatherapp.databinding.ItemForecastTimeBinding
import com.dido.weatherapp.domain.model.ForecastTime
import kotlin.math.floor

class ForecastTimeAdapter(
    layoutInflater: LayoutInflater,
    private val glide: RequestManager,
    ) : BaseListAdapter<ForecastTime, ItemForecastTimeBinding, ForecastTimeAdapter.ForecastViewHolder>(
    layoutInflater,
    ItemForecastTimeBinding::inflate,
    ForecastTimeDiffCallback
) {

    override fun itemViewHolder(
        viewBinding: ItemForecastTimeBinding,
        viewType: Int,
    ): ForecastTimeAdapter.ForecastViewHolder {
        return ForecastViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ForecastTimeAdapter.ForecastViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ForecastViewHolder(
        private val binding: ItemForecastTimeBinding,
    ) : BaseViewHolder<ForecastTime>(binding.root) {

        override fun bind(item: ForecastTime, position: Int) {
            binding.apply {
                tvTime.text = item.time
                tvWeather.text = "${floor(item.temperature).toInt()}°"
                glide.load(BuildConfig.ASSET_URL + item.iconWeather + "@2x.png")
                    .centerInside()
                    .into(ivWeather)
            }
        }
    }

    object ForecastTimeDiffCallback : DiffUtil.ItemCallback<ForecastTime>() {
        override fun areItemsTheSame(oldItem: ForecastTime, newItem: ForecastTime): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ForecastTime, newItem: ForecastTime): Boolean {
            return oldItem == newItem
        }
    }

}
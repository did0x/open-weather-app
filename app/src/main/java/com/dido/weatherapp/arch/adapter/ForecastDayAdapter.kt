package com.dido.weatherapp.arch.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.dido.weatherapp.BuildConfig
import com.dido.weatherapp.arch.base.BaseListAdapter
import com.dido.weatherapp.arch.base.BaseViewHolder
import com.dido.weatherapp.databinding.ItemForecastDayBinding
import com.dido.weatherapp.databinding.ItemForecastTimeBinding
import com.dido.weatherapp.domain.model.ForecastDay
import com.dido.weatherapp.domain.model.ForecastTime
import java.util.Locale
import kotlin.math.floor

class ForecastDayAdapter(
    layoutInflater: LayoutInflater,
    private val glide: RequestManager,
    ) : BaseListAdapter<ForecastDay, ItemForecastDayBinding, ForecastDayAdapter.ForecastViewHolder>(
    layoutInflater,
    ItemForecastDayBinding::inflate,
    ForecastDayDiffCallback
) {

    override fun itemViewHolder(
        viewBinding: ItemForecastDayBinding,
        viewType: Int,
    ): ForecastDayAdapter.ForecastViewHolder {
        return ForecastViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ForecastDayAdapter.ForecastViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ForecastViewHolder(
        private val binding: ItemForecastDayBinding,
    ) : BaseViewHolder<ForecastDay>(binding.root) {

        override fun bind(item: ForecastDay, position: Int) {
            binding.apply {
                tvDay.text = item.day.uppercase()
                tvTemperatureHigh.text = "${floor(item.temperatureHigh).toInt()}°"
                tvTemperatureLow.text = "${floor(item.temperatureLow).toInt()}°"
                tvDescription.text = item.descriptionWeather
                    .split(" ")
                    .joinToString(" ") { str ->
                        str.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                    }
                glide.load(BuildConfig.ASSET_URL + item.iconWeather + "@2x.png")
                    .centerInside()
                    .into(ivWeather)
            }
        }
    }

    object ForecastDayDiffCallback : DiffUtil.ItemCallback<ForecastDay>() {
        override fun areItemsTheSame(oldItem: ForecastDay, newItem: ForecastDay): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ForecastDay, newItem: ForecastDay): Boolean {
            return oldItem == newItem
        }
    }

}
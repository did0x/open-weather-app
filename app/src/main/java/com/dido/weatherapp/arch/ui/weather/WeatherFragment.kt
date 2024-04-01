package com.dido.weatherapp.arch.ui.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dido.weatherapp.arch.adapter.ForecastDayAdapter
import com.dido.weatherapp.arch.adapter.ForecastTimeAdapter
import com.dido.weatherapp.arch.base.BaseFragment
import com.dido.weatherapp.arch.ui.vm.WeatherViewModel
import com.dido.weatherapp.databinding.FragmentWeatherBinding
import com.dido.weatherapp.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.floor

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding, WeatherViewModel>(FragmentWeatherBinding::inflate) {

    private val forecastTimeAdapter by lazy { ForecastTimeAdapter(layoutInflater, Glide.with(requireContext())) }
    private val forecastDayAdapter by lazy { ForecastDayAdapter(layoutInflater, Glide.with(requireContext())) }

    override val viewModel: WeatherViewModel by viewModels()

    override fun initView(view: View, savedInstanceState: Bundle?) = with(binding) {
        rvForecastTime.adapter = forecastTimeAdapter
        rvForecastDay.adapter = forecastDayAdapter

        viewModel.getWeather(-6.175110, 106.865036)
        viewModel.weather.observe(viewLifecycleOwner, EventObserver {
            tvCity.text = it.cityName
            tvCurrentTemperature.text = "${floor(it.temperature).toInt()}°"
            tvCurrentTemperatureFeel.text = "Feels like ${floor(it.temperatureLike).toInt()}°"

            forecastTimeAdapter.submitList(it.listForecastTime)
            forecastDayAdapter.submitList(it.listForecastDay)
        })
    }
}
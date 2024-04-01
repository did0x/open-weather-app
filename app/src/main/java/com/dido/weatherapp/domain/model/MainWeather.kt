package com.dido.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainWeather(
    val cityName: String,
    val temperature: Double,
    val temperatureLike: Double,
    val listForecastDay: List<ForecastDay>,
    val listForecastTime: List<ForecastTime>,
    val listInfo: List<InfoWeather>,
) : Parcelable
package com.dido.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastDay(
    val day: String,
    val temperatureHigh: Double,
    val temperatureLow: Double,
    val iconWeather: String,
    val descriptionWeather : String,
) : Parcelable

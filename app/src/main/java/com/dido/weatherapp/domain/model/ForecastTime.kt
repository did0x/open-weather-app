package com.dido.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastTime(
    val time: String,
    val temperature: Double,
    val iconWeather: String,
) : Parcelable

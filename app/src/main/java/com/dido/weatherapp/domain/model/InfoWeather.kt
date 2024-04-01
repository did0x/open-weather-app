package com.dido.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoWeather(
    val title: String,
    val content: String,
    val description: String,
) : Parcelable

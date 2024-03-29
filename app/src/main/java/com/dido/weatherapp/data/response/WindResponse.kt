package com.dido.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class WindResponse(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val degrees: Int,
    @SerializedName("gust")
    val gust: Double,
)
package com.dido.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class ForecastResultResponse(
    @SerializedName("cod")
    val code: String,
    @SerializedName("message")
    val message: Int,
    @SerializedName("cnt")
    val numberTimestamp: Int,
    @SerializedName("list")
    val list: List<ForecastResponse>,
    @SerializedName("city")
    val city: CityResponse,
)
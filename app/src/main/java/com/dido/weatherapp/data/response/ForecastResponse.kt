package com.dido.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("dt")
    val dateTimeMillis: Long,
    @SerializedName("main")
    val main: MainResponse,
    @SerializedName("weather")
    val weather: List<WeatherResponse>,
    @SerializedName("clouds")
    val clouds: CloudsResponse,
    @SerializedName("wind")
    val wind: WindResponse,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("rain")
    val rain: RainResponse?,
    @SerializedName("snow")
    val snow: SnowResponse?,
    @SerializedName("sys")
    val sys: SysResponse,
    @SerializedName("dt_txt")
    val dateTime: String,
)
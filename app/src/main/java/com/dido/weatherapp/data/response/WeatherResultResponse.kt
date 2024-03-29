package com.dido.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class WeatherResultResponse(
    @SerializedName("coord")
    val coordinate: CoordinateResponse,
    @SerializedName("weather")
    val weather: WeatherResponse,
    @SerializedName("base")
    val base: String,
    @SerializedName("main")
    val main: MainResponse,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("wind")
    val wind: WindResponse,
    @SerializedName("clouds")
    val clouds: CloudsResponse,
    @SerializedName("rain")
    val rain: RainResponse?,
    @SerializedName("snow")
    val snow: SnowResponse?,
    @SerializedName("dt")
    val dateTimeMillis: Long,
    @SerializedName("sys")
    val sys: SysResponse,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("cod")
    val code: String,
)
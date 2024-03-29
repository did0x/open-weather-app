package com.dido.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coordinate: CoordinateResponse,
    @SerializedName("country")
    val country: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
)
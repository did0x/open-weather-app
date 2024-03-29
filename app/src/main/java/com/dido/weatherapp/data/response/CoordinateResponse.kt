package com.dido.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class CoordinateResponse(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val long: Double
)
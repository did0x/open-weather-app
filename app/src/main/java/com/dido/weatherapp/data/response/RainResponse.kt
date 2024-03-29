package com.dido.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class RainResponse(
    @SerializedName("1h")
    val oneHour : Double?,
    @SerializedName("3h")
    val threeHour : Double?
)
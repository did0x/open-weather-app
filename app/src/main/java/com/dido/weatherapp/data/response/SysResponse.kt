package com.dido.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class SysResponse(
    @SerializedName("pod")
    val partOfDay: String
)
package com.dido.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class CloudsResponse(
    @SerializedName("all")
    val all: Int
)
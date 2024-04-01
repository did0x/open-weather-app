package com.dido.weatherapp.data

import com.dido.weatherapp.data.response.ForecastResultResponse
import com.dido.weatherapp.data.response.WeatherResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("units") units: String = "metric"
    ): Response<ForecastResultResponse>

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("units") units: String = "metric"
    ): Response<WeatherResultResponse>

}
package com.dido.weatherapp.data.response

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") long: Double
    ): ForecastResultResponse

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") long: Double
    ): WeatherResultResponse

}
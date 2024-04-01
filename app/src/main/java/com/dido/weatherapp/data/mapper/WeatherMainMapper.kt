package com.dido.weatherapp.data.mapper

import com.dido.weatherapp.data.base.Mapper
import com.dido.weatherapp.data.response.ForecastResponse
import com.dido.weatherapp.data.response.ForecastResultResponse
import com.dido.weatherapp.domain.model.ForecastDay
import com.dido.weatherapp.domain.model.ForecastTime
import com.dido.weatherapp.domain.model.InfoWeather
import com.dido.weatherapp.domain.model.MainWeather
import com.dido.weatherapp.utils.DateUtils
import java.util.Calendar
import javax.inject.Inject
import kotlin.math.abs

class WeatherMainMapper @Inject constructor() : Mapper<ForecastResultResponse, MainWeather> {

    override fun from(source: ForecastResultResponse): MainWeather {
        return source.let {
            MainWeather(
                cityName = it.city.name,
                temperature = it.list[0].main.temp,
                temperatureLike = it.list[0].main.feelsLike,
                listForecastDay = provideForecastDay(it.list),
                listForecastTime = provideForecastTime(it.list),
                listInfo = provideInfoWeather(it)
            )
        }
    }

    private fun provideForecastDay(forecast: List<ForecastResponse>): List<ForecastDay> {
        val groupedForecast =  forecast.groupBy {
            val date = DateUtils.formatDate(it.dateTime, DateUtils.DATE_FULL, DateUtils.DATE_DAY_SHORT)
            date
        }.mapValues { (_, forecasts) ->
            val tempHigh = forecasts.map { it.main.tempMax }.max()
            val tempLow = forecasts.map { it.main.tempMin }.min()

            ForecastDay(
                day = DateUtils.formatDate(forecasts[0].dateTime, resultFormat = DateUtils.DATE_DAY_SHORT),
                temperatureHigh = tempHigh,
                temperatureLow = tempLow,
                iconWeather = forecasts[0].weather[0].icon,
                descriptionWeather = forecasts[0].weather[0].description,
            )
        }

        return groupedForecast.toList().map { it.second }
    }

    private fun provideForecastTime(forecast: List<ForecastResponse>): List<ForecastTime> {
        val calendar = Calendar.getInstance()
        val currentTimeMillis = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val nextDayTimeMillis =  calendar.timeInMillis
        return forecast.filter {
            val dateMillis = DateUtils.getDateMillis(it.dateTime, DateUtils.DATE_FULL)
            dateMillis > currentTimeMillis && dateMillis < nextDayTimeMillis
        }.map {
            ForecastTime(
                time = DateUtils.formatDate(it.dateTime, resultFormat = DateUtils.TIME_AM_PM),
                iconWeather = it.weather[0].icon,
                temperature = it.main.temp
            )
        }
    }

    private fun provideInfoWeather(forecast: ForecastResultResponse): List<InfoWeather> {
        val currentTimeMillis = System.currentTimeMillis()
        val nearestForecast = forecast.list.minByOrNull {
            val dateMillis = DateUtils.getDateMillis(it.dateTime, DateUtils.DATE_FULL)
            abs(dateMillis - currentTimeMillis)
        }

        val infoWeather = mutableListOf<InfoWeather>()
        infoWeather.add(
            InfoWeather(
                title = "Sunrise",
                content = "${forecast.city.sunrise}",
                description = ""
            )
        )

        infoWeather.add(
            InfoWeather(
                title = "Sunset",
                content = "${forecast.city.sunset}",
                description = ""
            )
        )

        infoWeather.add(
            InfoWeather(
                title = "Humidity",
                content = "${nearestForecast?.main?.humidity}",
                description = ""
            )
        )

        infoWeather.add(
            InfoWeather(
                title = "Wind",
                content = "${nearestForecast?.wind?.speed}",
                description = ""
            )
        )

        return infoWeather
    }
}
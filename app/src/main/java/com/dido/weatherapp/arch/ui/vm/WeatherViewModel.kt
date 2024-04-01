package com.dido.weatherapp.arch.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dido.weatherapp.arch.base.BaseViewModel
import com.dido.weatherapp.data.Api
import com.dido.weatherapp.data.mapper.WeatherMainMapper
import com.dido.weatherapp.domain.model.MainWeather
import com.dido.weatherapp.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val api: Api
) : BaseViewModel() {

    private val _weather = MutableLiveData<Event<MainWeather>>()
    val weather: LiveData<Event<MainWeather>> = _weather

    fun getWeather(lat: Double, long: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoading()
                val response = api.getForecast(lat, long)
                val body = response.body()
                with (response) {
                     when {
                         isSuccessful -> {
                             body?.let { result ->
                                 _weather.postValue(Event(WeatherMainMapper().from(result)))
                             }
                         }
                         else -> setError(response.message())
                    }
                }
            } catch (throwable: Throwable) {
                _weather.postValue(Event(MainWeather("Error", 0.0, 0.0, emptyList(), emptyList(), emptyList())))
            } finally {
                hideLoading()
            }
        }
    }

}
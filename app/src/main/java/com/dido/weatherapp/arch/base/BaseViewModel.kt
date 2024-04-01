package com.dido.weatherapp.arch.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dido.weatherapp.utils.Event

abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Event<Boolean>>()
    private val _error = MutableLiveData<Event<String>>()

    protected fun showLoading() {
        _loading.postValue(Event(true))
    }

    protected fun hideLoading() {
        _loading.postValue(Event(false))
    }

    protected fun setError(message: String) {
        _error.postValue(Event(message))
    }
}
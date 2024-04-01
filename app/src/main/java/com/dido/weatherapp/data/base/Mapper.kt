package com.dido.weatherapp.data.base

interface Mapper<T, E> {

    fun from(source: T): E
}
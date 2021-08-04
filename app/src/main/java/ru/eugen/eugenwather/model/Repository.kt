package ru.eugen.eugenwather.model

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}
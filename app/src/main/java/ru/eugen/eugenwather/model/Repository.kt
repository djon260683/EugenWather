package ru.eugen.eugenwather.model

interface Repository {
//    fun getWeatherFromServer(): Weather
//    fun getWeatherFromLocalStorage(): Weather
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}
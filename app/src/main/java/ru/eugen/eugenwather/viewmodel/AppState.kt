package ru.eugen.eugenwather.viewmodel

import ru.eugen.eugenwather.model.Weather

sealed class AppState{
    data class Success(val weatherData: List<Weather>) : AppState()
//    data class Error(val error: Throwable) : AppState()
object Error : AppState()
    object Loading : AppState()
}

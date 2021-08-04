package ru.eugen.eugenwather.viewmodel

import ru.eugen.eugenwather.model.Weather

sealed class AppState{
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

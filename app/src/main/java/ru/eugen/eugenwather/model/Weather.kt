package ru.eugen.eugenwather.model

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 29,
    val feelsLike: Int = 32
)
fun getDefaultCity() = City("Москва", 55.755826, 37.617299900000035)

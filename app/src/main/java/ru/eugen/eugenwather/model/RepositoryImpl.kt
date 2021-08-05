package ru.eugen.eugenwather.model

class RepositoryImpl : Repository{
//    override fun getWeatherFromServer(): Weather {
//        return Weather()
//    }
//    override fun getWeatherFromLocalStorage(): Weather {
//        return Weather()
//    }
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }
    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }
    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getWorldCities()
    }
}
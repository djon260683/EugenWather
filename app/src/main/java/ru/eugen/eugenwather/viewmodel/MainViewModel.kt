package ru.eugen.eugenwather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.eugen.eugenwather.model.Repository
import ru.eugen.eugenwather.model.RepositoryImpl
import java.lang.Thread.sleep
import java.util.*

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getWeather() = getDataFromLocalSource()
    fun getWeatherFromRemoteSource() = getDataFromLocalSource()
    private fun getDataFromLocalSource() {
        Thread {
            liveDataToObserve.postValue(AppState.Loading)
            sleep(2000)
            val i:Int = Random().nextInt(2)
            when(i) {
                0->{liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))}
                1->{liveDataToObserve.postValue(AppState.Error)}
            }
        }.start()
    }
}
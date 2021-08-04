package ru.eugen.eugenwather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.eugen.eugenwather.model.Repository
import ru.eugen.eugenwather.model.RepositoryImpl
import java.lang.Thread.sleep

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
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
        }.start()
    }
}
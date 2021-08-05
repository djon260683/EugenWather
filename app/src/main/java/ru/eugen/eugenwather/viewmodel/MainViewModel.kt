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
    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(true)
    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource( false)
    fun getWeatherFromRemoteSource() = getDataFromLocalSource(true)
    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(if (isRussian)
                repositoryImpl.getWeatherFromLocalStorageRus() else
                repositoryImpl.getWeatherFromLocalStorageWorld()))
        }.start()
    }


}
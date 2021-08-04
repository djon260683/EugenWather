package ru.eugen.eugenwather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.eugen.eugenwather.R
import ru.eugen.eugenwather.databinding.MainFragmentBinding
import ru.eugen.eugenwather.model.Weather
import ru.eugen.eugenwather.viewmodel.AppState
import ru.eugen.eugenwather.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = Observer<Any> { renderData(it as AppState) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getWeather()
    }
    private fun setData(weatherData: Weather) {
        binding.cityName.text = weatherData.city.city
        binding.cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            weatherData.city.lat.toString(),
            weatherData.city.lon.toString()
        )
        binding.temperatureValue.text = weatherData.temperature.toString()
        binding.feelsLikeValue.text = weatherData.feelsLike.toString()
    }
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val weatherData = appState.weatherData
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainView, "Success", Snackbar.LENGTH_LONG).show()
                setData(weatherData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getWeather() }
                    .show()
            }
        }
    }
}
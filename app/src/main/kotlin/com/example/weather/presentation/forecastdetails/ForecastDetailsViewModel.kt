package com.example.weather.presentation.forecastdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.ForecastDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ForecastDetailsViewModel @Inject constructor(private val forecastDetailsUseCase: ForecastDetailsUseCase) :
    ViewModel() {

    internal val forecastDetails: LiveData<ForecastDetails>
        get() = _forecastDetails
    private val _forecastDetails: MutableLiveData<ForecastDetails> = MutableLiveData()

    fun loadData(date: Long) {
        viewModelScope.launch {
            val forecastDetails =
                forecastDetailsUseCase.execute(params = ForecasrDetailsParams(date))

            _forecastDetails.value = ForecastDetails(
                temperature = forecastDetails.temperature.toInt(),
                weatherPerception = "${forecastDetails.weatherPerception.toInt()}",
                weatherCondition = forecastDetails.weatherCondition,
                weatherDescription = forecastDetails.weatherDescription
            )
        }
    }
}
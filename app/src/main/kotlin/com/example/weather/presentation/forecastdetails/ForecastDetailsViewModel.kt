package com.example.weather.presentation.forecastdetails

import androidx.lifecycle.*
import com.example.weather.base.BaseViewModel
import com.example.weather.domain.ForecastDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ForecastDetailsViewModel @Inject constructor(
    private val forecastDetailsUseCase: ForecastDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {

    internal val forecastDetails: LiveData<ForecastDetails>
        get() = _forecastDetails
    private val _forecastDetails: MutableLiveData<ForecastDetails> = MutableLiveData()

    private val args by navArgs<ForecastDetailsFragmentArgs>()

    fun loadData() {
        viewModelScope.launch {
            val forecastDetails = forecastDetailsUseCase.execute(params = ForecasrDetailsParams(args.date))
            _forecastDetails.value = ForecastDetails(
                temperature = forecastDetails.temperature.toInt(),
                weatherPerception = "${forecastDetails.weatherPerception.toInt()}",
                weatherCondition = forecastDetails.weatherCondition,
                weatherDescription = forecastDetails.weatherDescription
            )
        }
    }
}
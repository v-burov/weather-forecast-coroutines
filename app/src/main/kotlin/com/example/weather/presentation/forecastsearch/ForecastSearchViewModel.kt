package com.example.weather.presentation.forecastsearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weather.R
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForecastSearchViewModel @Inject constructor() : ViewModel() {

    internal val success: LiveData<String>
        get() = _success
    private val _success: LiveEvent<String> = LiveEvent()

    internal val error: LiveData<Int>
        get() = _error
    private val _error: LiveEvent<Int> = LiveEvent()

    fun validateCityName(cityName: String) {
        if (cityName.isEmpty()) {
            _error.value = R.string.search_city_name_empty
        } else {
            _success.value = cityName
        }
    }
}
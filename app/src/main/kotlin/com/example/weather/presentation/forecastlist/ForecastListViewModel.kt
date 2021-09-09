package com.example.weather.presentation.forecastlist

import androidx.lifecycle.*
import com.example.weather.domain.ForecastSearchParams
import com.example.weather.domain.ForecastSearchResult
import com.example.weather.domain.ForecastSearchUseCase
import com.example.weather.presentation.forecastlist.ForecastListFragment.Companion.PARAMS_CITY_NAME
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ForecastListViewModel @Inject constructor(
    private val forecastSearchUseCase: ForecastSearchUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(), LifecycleObserver {

    internal val forecasts: LiveData<List<ForecastListItem>>
        get() = _forecasts
    private val _forecasts: MutableLiveData<List<ForecastListItem>> = MutableLiveData()

    internal val error: LiveData<String>
        get() = _error
    private val _error: LiveEvent<String> = LiveEvent()

    private val cityName: String = savedStateHandle.get<String>(PARAMS_CITY_NAME) ?: ""

    fun fetchForecast() {
        viewModelScope.launch {
            when (val response = forecastSearchUseCase.execute(ForecastSearchParams(cityName))) {
                is ForecastSearchResult.Success -> {
                    _forecasts.value = response.forecastList.map {
                        ForecastListItem(
                            date = it.date,
                            weatherCondition = it.weatherCondition,
                            temperature = it.temperature.toInt()
                        )
                    }
                }
                is ForecastSearchResult.Error -> {
                    _error.value = response.message
                }
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        fetchForecast()
    }

}
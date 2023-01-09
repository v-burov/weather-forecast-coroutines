# Weather forecast (MVVM-Coroutines)

A simple application that provides a weather forecast.

- The app displays the user an input field.
- The user enters a city name and presses lookup.
- The app sends api request and displays the user a weather forecast.

# Goal

To show how to implement Clean architecture using MVVM for presentation layer
and kotlin coroutines for background tasks.

# Preview

<img src="https://github.com/v-burov/weather-forecast/blob/main/forecast_coroutines.gif" width="270" height="480">

# Stack:

Android Architecture Components (ViewModel, LiveData, Navigation, Lifecycle),
Retrofit, Dagger Hilt, Coroutines.

# Good to improve:

1. Network helper to handle network connection.
2. Diff utils for forecast list.
3. Persistence storage (room or greendao)
4. Drop down list to choose city because the same city may be present in
   different countries.
5. Add search by postal code.
6. Improve UI design. Add date to each forecast to have an understanding for
   which day the forecast is shown.
7. Add unit tests.
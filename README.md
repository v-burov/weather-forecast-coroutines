# Weather forecast (MVVM - Clean)

The goal of this project is to show how to implement Clean architecture using MVVM for presentation layer and kotlin coroutines for background tasks.

![](https://github.com/v-burov/weather-forecast/blob/main/forecast_coroutines.gif =540x960)

# Stack: 
Android Architecture Components (ViewModel, LiveData, Navigation, Lifecycle), Retrofit, Dagger Hilt, Coroutines.

# Good to improve:
1. Network helper to handle network connection.
2. Diff utils for forecast list.
3. Persistence storage (room or greendao)
4. Drop down list to choose city because the same city may be present in different countries.
5. Add search by postal code.
6. Improve UI design. 
package com.example.weather.data.remote

internal sealed class RemoteSourceResponse<out T> {

    class Success<out T>(val data: T) : RemoteSourceResponse<T>()
    class Error(val exception: Throwable) : RemoteSourceResponse<Nothing>()

    companion object {
        inline fun <T> on(f: () -> T): RemoteSourceResponse<T> = try {
            Success(f())
        } catch (ex: Exception) {
            Error(ex)
        }
    }
}
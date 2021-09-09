package com.example.weather.base

interface UseCase {

    interface UseCaseWithoutParams<T> {
        suspend fun execute(): T
    }

    interface UseCaseWithParams<T, P : Params> {
        suspend fun execute(params: P): T
    }
}
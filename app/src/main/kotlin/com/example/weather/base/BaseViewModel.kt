package com.example.weather.base

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy

const val BUNDLE_ARGS = "BaseViewModel.extras.BUNDLE_ARGS"

abstract class BaseViewModel (private val savedStateHandle: SavedStateHandle) : ViewModel(), LifecycleObserver {
    val arguments get() = savedStateHandle.get<Bundle>(BUNDLE_ARGS)
    @MainThread
    inline fun <reified Args : NavArgs> navArgs() = NavArgsLazy(Args::class) {
        arguments ?: throw IllegalStateException("ViewModel $this has null arguments")
    }
}
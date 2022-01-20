package com.troshchii.reddit.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateClass<T>(initial: T) {

    private val _state: MutableStateFlow<T> = MutableStateFlow(initial)
    val state: StateFlow<T> = _state.asStateFlow()

    fun update(value: T) {
        _state.value = value
    }
}
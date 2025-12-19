package com.example.testworkmate.common.utils

interface ViewModelEventHandler<T> {
    fun onEvent(event: T)
}
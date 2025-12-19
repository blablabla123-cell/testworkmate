package com.example.testworkmate.details.presentation.view_model

sealed class CountryDetailsEvent {
    data class Init(val countryName: String): CountryDetailsEvent()
    data object Dispose: CountryDetailsEvent()
}
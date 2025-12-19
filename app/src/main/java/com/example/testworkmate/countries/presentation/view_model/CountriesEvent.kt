package com.example.testworkmate.countries.presentation.view_model

sealed class CountriesEvent {
    data object Init: CountriesEvent()
    data object Dispose: CountriesEvent()
    data object ClearCache: CountriesEvent()
}
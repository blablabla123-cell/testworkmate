package com.example.testworkmate.countries.presentation.view_model

import com.example.testworkmate.common.domain.model.Country

data class CountriesState (
    val countries: List<Country> = emptyList(),

    val loadingMessage: Int? = null,
    val error: String? = null,
)
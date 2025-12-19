package com.example.testworkmate.details.presentation.view_model

import com.example.testworkmate.common.domain.model.Country

data class CountryDetailsState (
    val countryDetails: Country? = null,

    val loadingMessage: Int? = null,
    val error: String? = null,
)
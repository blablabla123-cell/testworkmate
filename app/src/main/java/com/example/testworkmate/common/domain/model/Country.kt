package com.example.testworkmate.common.domain.model

data class Country (
    val name: String,
    val flag: String,
    val flagPictureUrl: String? = null,
    val flagContentDescription: String? = null,
    val capital: String? = null,
    val continent: String? = null,
    val language: String? = null,
    val currency: String? = null,
)
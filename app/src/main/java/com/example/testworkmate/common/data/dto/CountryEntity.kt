package com.example.testworkmate.common.data.dto

class CountryEntity (
    val name: CountryName,
    val flag: String,
    val flags: CountryFlags,
    val capital: List<String> = emptyList(),
    val continents: List<String> = emptyList(),
    val languages: Map<String, String> = emptyMap(),
    val currencies: Map<String, Currency> = emptyMap(),
)
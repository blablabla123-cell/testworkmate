package com.example.testworkmate.common.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryDto(
    @PrimaryKey(autoGenerate = false) val name: String,
    val flag: String,
    val flagPictureUrl: String? = null,
    val flagContentDescription: String? = null,
    val capital: String? = null,
    val continent: String? = null,
    val language: String? = null,
    val currency: String? = null,
    val cachedAt: Long,
)
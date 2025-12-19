package com.example.testworkmate.common.domain.repository

interface LocalStorageRepository {
    suspend fun clearCache()
}
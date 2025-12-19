package com.example.testworkmate.common.data.repository

import com.example.testworkmate.common.domain.repository.LocalStorageRepository
import com.example.testworkmate.common.room.daos.CountriesDao

class LocalStorageRepositoryImplementation(
    val dao: CountriesDao,
) : LocalStorageRepository {
    override suspend fun clearCache() {
        return dao.clearCache()
    }
}
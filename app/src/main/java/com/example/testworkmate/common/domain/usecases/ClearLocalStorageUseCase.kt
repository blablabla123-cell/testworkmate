package com.example.testworkmate.common.domain.usecases

import com.example.testworkmate.common.domain.repository.LocalStorageRepository
import com.example.testworkmate.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClearLocalStorageUseCase(
    val repository: LocalStorageRepository
) {
    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())

            repository.clearCache()

            emit(Resource.Success(true))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.message ?: "Unexpected error occurred"))
        }
    }
}
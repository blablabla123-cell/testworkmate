package com.example.testworkmate.common

import android.app.Application
import androidx.room.Room
import com.example.testworkmate.common.data.repository.LocalStorageRepositoryImplementation
import com.example.testworkmate.common.domain.CommonUseCases
import com.example.testworkmate.common.domain.repository.LocalStorageRepository
import com.example.testworkmate.common.domain.usecases.ClearLocalStorageUseCase
import com.example.testworkmate.common.retrofit.LoggingInterceptor
import com.example.testworkmate.countries.data.data_source.CountriesApi
import com.example.testworkmate.countries.data.repository.CountriesRepositoryImplementation
import com.example.testworkmate.countries.domain.repository.CountriesRepository
import com.example.testworkmate.common.room.WorkmateDatabase
import com.example.testworkmate.countries.domain.CountriesUseCases
import com.example.testworkmate.countries.domain.usecases.FetchCountriesUseCase
import com.example.testworkmate.details.data.data_source.CountryDetailsApi
import com.example.testworkmate.details.data.repository.CountryDetailsRepositoryImplementation
import com.example.testworkmate.details.domain.CountryDetailsUseCases
import com.example.testworkmate.details.domain.repository.CountryDetailsRepository
import com.example.testworkmate.details.domain.usecases.FetchCountryDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    /// RETROFIT
    // Provide OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: LoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    // Provide Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(CoreConstants.SERVER_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Provides
    @Singleton
    fun provideApplicationDatabase(application: Application): WorkmateDatabase {
        return Room.databaseBuilder(
            application,
            WorkmateDatabase::class.java,
            CoreConstants.DATABASE_NAME,
        ).build()
    }


    @Provides
    @Singleton
    fun provideCountriesApi(retrofit: Retrofit): CountriesApi {
        return retrofit.create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountriesRepository(api: CountriesApi, dao: WorkmateDatabase): CountriesRepository {
        return CountriesRepositoryImplementation(api, dao.countriesDao)
    }

    @Provides
    @Singleton
    fun provideCountriesUseCases(repository: CountriesRepository): CountriesUseCases {
        return CountriesUseCases(
            FetchCountriesUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCountryDetailsApi(retrofit: Retrofit): CountryDetailsApi {
        return retrofit.create(CountryDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalStorageRepository(
        dao: WorkmateDatabase
    ): LocalStorageRepository {
        return LocalStorageRepositoryImplementation(dao.countriesDao)
    }

    @Provides
    @Singleton
    fun provideClearLocalStorageRepository(
        repository: LocalStorageRepository
    ): CommonUseCases {
        return CommonUseCases(
            ClearLocalStorageUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCountryDetailsRepository(api: CountryDetailsApi, dao: WorkmateDatabase): CountryDetailsRepository {
        return CountryDetailsRepositoryImplementation(api, dao.countriesDao)
    }

    @Provides
    @Singleton
    fun provideCountryDetailsUseCases(repository: CountryDetailsRepository): CountryDetailsUseCases {
        return CountryDetailsUseCases(
            FetchCountryDetailsUseCase(repository)
        )
    }
}
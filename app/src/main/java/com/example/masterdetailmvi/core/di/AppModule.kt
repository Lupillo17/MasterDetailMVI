package com.example.masterdetailmvi.core.di

import com.example.masterdetailmvi.data.repository.CityRepositoryImpl
import com.example.masterdetailmvi.domain.repository.CityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient()
    }

    @Provides
    @Singleton
    fun provideCityRepository(httpClient: HttpClient): CityRepository {
        return CityRepositoryImpl(httpClient)
    }

}
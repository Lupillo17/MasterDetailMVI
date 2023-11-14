package com.example.masterdetailmvi.data.providers

import android.accounts.NetworkErrorException
import com.example.masterdetailmvi.data.implementations.SchoolImpl
import com.example.masterdetailmvi.data.utils.SchoolUtilsAPI
import com.example.masterdetailmvi.domain.services.ISchool
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkProvider {
    private fun provideHttpClient(
        baseUrl: String,
    ): HttpClient =
        HttpClient(OkHttp) {
            expectSuccess = true

            install(Logging)

            defaultRequest {
                url(urlString = baseUrl)
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

            install(HttpRequestRetry) {
                maxRetries = 3
                retryIf { _, httpResponse ->
                    httpResponse.status.isSuccess().not()
                }
                retryOnExceptionIf { _, throwable ->
                    throwable is NetworkErrorException
                }
                delayMillis { httpRetryRequest ->
                    httpRetryRequest * 5_000L
                }
            }
        }

    @Singleton
    @Provides
    fun provideSchoolService(): ISchool {
        val httpClient = provideHttpClient(baseUrl = SchoolUtilsAPI.BASE_URL)
        return SchoolImpl(
            httpClient = httpClient
        )
    }
}
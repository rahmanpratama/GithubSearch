package io.github.rahman.githubsearch.base

import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

internal class GitHubConnectionModule(private val cache: Cache) {

    // Get Device model information (Optional for GitHub API)
    fun getDeviceModel(): String {
        return if (Build.MODEL.startsWith(Build.MANUFACTURER, ignoreCase = true)) {
            Build.MODEL
        } else {
            Build.MANUFACTURER + " " + Build.MODEL
        }
    }

    // Get OS Version (Optional for GitHub API)
    fun getOs(): String {
        return Build.VERSION.SDK_INT.toString()
    }

    // Setting up the HTTP Request Interceptor for GitHub API
    fun setHttpRequestInterceptor() = Interceptor {
        var request = it.request()
        val requestBuilder = request.newBuilder()

        // Add GitHub-specific headers
        requestBuilder.addHeader("Accept", "application/vnd.github.v3+json")
        requestBuilder.addHeader("Authorization", "Bearer ${cache.authToken}") // Auth Token (OAuth)

        // Optional headers for device info (you can skip this for GitHub, it's not required)
        requestBuilder.addHeader("x-device-model", getDeviceModel())
        requestBuilder.addHeader("x-os-version", getOs())

        request = requestBuilder.build()
        it.proceed(request)
    }

    // Setting up the HTTP Logging Interceptor for debugging purposes
    private fun setHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }

    // Providing Retrofit instance with OkHttpClient
    fun provideRetrofit(): Retrofit {

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(setHttpRequestInterceptor()) // Add the request interceptor
            .addInterceptor(setHttpLoggingInterceptor()) // Add logging interceptor
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

        val baseUrlGitHub = "https://api.github.com/" // Base URL for GitHub API

        return Retrofit.Builder()
            .baseUrl(baseUrlGitHub)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create()) // Using Moshi for JSON parsing
            .build()
    }
}

package dev.harold.retrofitpath.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.harold.retrofitpath.network.AmphibiansApiService
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val amphibianRepository: AmphibianRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val apiService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    override val amphibianRepository: AmphibianRepository by lazy {
        NetworkAmphibianRepository(apiService)
    }
}
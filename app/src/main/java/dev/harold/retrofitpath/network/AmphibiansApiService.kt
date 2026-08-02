package dev.harold.retrofitpath.network

import dev.harold.retrofitpath.model.AmphibianInfo
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<AmphibianInfo>
}
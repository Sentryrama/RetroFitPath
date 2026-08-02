package dev.harold.retrofitpath.data

import dev.harold.retrofitpath.model.AmphibianInfo
import dev.harold.retrofitpath.network.AmphibiansApiService

interface AmphibianRepository {
    suspend fun getAmphibians(): List<AmphibianInfo>
}

class NetworkAmphibianRepository(private val apiService: AmphibiansApiService) : AmphibianRepository {
    override suspend fun getAmphibians(): List<AmphibianInfo> {
        return apiService.getAmphibians()
    }
}
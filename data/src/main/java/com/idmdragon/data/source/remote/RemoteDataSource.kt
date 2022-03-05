package com.idmdragon.data.source.remote

import com.idmdragon.data.BuildConfig
import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.FisheryResponse
import com.idmdragon.data.source.remote.service.FisheryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val fisheryService: FisheryService) {

    fun getAllFishery(): Flow<ApiResponse<List<FisheryResponse>>> =
        flow {
            try {
                val response = fisheryService.getListItem(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}
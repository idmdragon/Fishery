package com.idmdragon.data.source.remote

import com.idmdragon.data.BuildConfig
import com.idmdragon.data.source.remote.response.*
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

    fun getListAreaItem(): Flow<ApiResponse<List<AreaResponse>>> =
        flow {
            try {
                val response = fisheryService.getListAreaItem(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getListSize(): Flow<ApiResponse<List<SizeResponse>>> =
        flow {
            try {
                val response = fisheryService.getListSize(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)


    fun addFishery(
        uuid: String,
        commodity: String,
        areaProvince: String,
        areaCity: String,
        size: String,
        price: String,
        tgl_parsed: String,
        timestamp: String
    ): Flow<ApiResponse<PostResponse>> =
        flow {
            try {
                val fisheryBody = FisheryBody(
                   uuid, commodity, areaProvince, areaCity, size, price, tgl_parsed, timestamp
                )
                val response = fisheryService.addListItem(
                        BuildConfig.API_KEY,
                        fisheryBody.asRequestBody(),
                    )
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

}
package com.idmdragon.data.repository

import com.idmdragon.data.mapper.toEntities
import com.idmdragon.data.mapper.toFlowModels
import com.idmdragon.data.source.NetworkBoundResource
import com.idmdragon.data.source.local.LocalDataSource
import com.idmdragon.data.source.remote.RemoteDataSource
import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.AreaResponse
import com.idmdragon.data.source.remote.response.FisheryResponse
import com.idmdragon.data.source.remote.response.SizeResponse
import com.idmdragon.domain.model.Area
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.model.Size
import com.idmdragon.domain.repository.FisheryRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class FisheryRepositoryImpl(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : FisheryRepository {

    override fun getAllFishery(): Flow<Resource<List<Fishery>>> =
        object : NetworkBoundResource<List<Fishery>, List<FisheryResponse>>() {

            override fun shouldFetch(data: List<Fishery>?): Boolean =
                true

            override fun loadFromDB(): Flow<List<Fishery>> =
                local.getAllFishery().toFlowModels()

            override suspend fun createCall(): Flow<ApiResponse<List<FisheryResponse>>> =
                remote.getAllFishery()

            override suspend fun saveCallResult(data: List<FisheryResponse>) {
                data.filter { fisheryResponse ->
                    fisheryResponse.uuid != null && fisheryResponse.price != null
                }.let {
                    local.insertListFishery(it.toEntities())
                }
            }

        }.asFlow()


    override fun getAllArea(): Flow<Resource<List<Area>>> =
        object : NetworkBoundResource<List<Area>, List<AreaResponse>>() {

            override fun shouldFetch(data: List<Area>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun saveCallResult(data: List<AreaResponse>) {
                local.insertArea(data.toEntities())
            }

            override fun loadFromDB(): Flow<List<Area>> =
                local.getAllArea().toFlowModels()

            override suspend fun createCall(): Flow<ApiResponse<List<AreaResponse>>> =
                remote.getListAreaItem()

        }.asFlow()

    override fun getAllSize(): Flow<Resource<List<Size>>> =
        object : NetworkBoundResource<List<Size>, List<SizeResponse>>() {

            override fun shouldFetch(data: List<Size>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun saveCallResult(data: List<SizeResponse>) {
                local.insertSize(data.toEntities())
            }

            override fun loadFromDB(): Flow<List<Size>> =
                local.getAllSize().toFlowModels()

            override suspend fun createCall(): Flow<ApiResponse<List<SizeResponse>>> =
                remote.getListSize()

        }.asFlow()

    override fun searchItem(query: String): Flow<Resource<List<Fishery>>> =
        object : NetworkBoundResource<List<Fishery>, List<FisheryResponse>>() {

            override fun shouldFetch(data: List<Fishery>?): Boolean =
                data.isNullOrEmpty()

            override fun loadFromDB(): Flow<List<Fishery>> =
                local.searchItem(query).toFlowModels()

            override suspend fun createCall(): Flow<ApiResponse<List<FisheryResponse>>> =
                remote.getAllFishery()

            override suspend fun saveCallResult(data: List<FisheryResponse>) {
                data.filter { fisheryResponse ->
                    fisheryResponse.uuid != null && fisheryResponse.price != null
                }.let {
                    local.insertListFishery(it.toEntities())
                }
            }

        }.asFlow()
}


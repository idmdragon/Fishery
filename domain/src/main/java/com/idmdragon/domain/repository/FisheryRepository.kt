package com.idmdragon.domain.repository

import com.idmdragon.domain.model.Area
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.model.Size
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FisheryRepository {
    fun getAllFishery(query: String, queryArea: Area): Flow<Resource<List<Fishery>>>
    fun getAllArea(): Flow<Resource<List<Area>>>
    fun getAllSize(): Flow<Resource<List<Size>>>
    fun searchItem(query: String): Flow<Resource<List<Fishery>>>
}
package com.idmdragon.domain.repository

import com.idmdragon.domain.model.Area
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FisheryRepository {
    fun getAllFishery(): Flow<Resource<List<Fishery>>>
    fun getAllArea(): Flow<Resource<List<Area>>>
}
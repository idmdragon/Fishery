package com.idmdragon.domain.usecase

import com.idmdragon.domain.model.Area
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.model.Size
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FisheryUsecase {
    fun getAllFishery(): Flow<Resource<List<Fishery>>>
    fun getAllArea(): Flow<Resource<List<Area>>>
    fun getAllSize(): Flow<Resource<List<Size>>>

}
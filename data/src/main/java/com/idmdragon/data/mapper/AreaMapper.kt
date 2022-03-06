package com.idmdragon.data.mapper

import com.idmdragon.data.source.local.entity.AreaEntity
import com.idmdragon.data.source.local.entity.FisheryEntity
import com.idmdragon.data.source.remote.response.AreaResponse
import com.idmdragon.data.source.remote.response.FisheryResponse
import com.idmdragon.domain.model.Area
import com.idmdragon.domain.model.Fishery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun AreaResponse.toEntity(): AreaEntity =
    AreaEntity( city, province )

fun AreaEntity.toModel(): Area =
    Area( city, province)

fun List<AreaResponse>.toEntities(): List<AreaEntity> =
    this.map {
        it.toEntity()
    }

fun List<AreaEntity>.toModels(): List<Area> =
    this.map {
        it.toModel()
    }

fun Flow<List<AreaEntity>>.toFlowModels(): Flow<List<Area>> =
    this.map {
        it.toModels()
    }
package com.idmdragon.data.mapper

import com.idmdragon.data.source.local.entity.FisheryEntity
import com.idmdragon.data.source.local.entity.SizeEntity
import com.idmdragon.data.source.remote.response.FisheryResponse
import com.idmdragon.data.source.remote.response.SizeResponse
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.model.Size
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun SizeResponse.toEntity(): SizeEntity =
    SizeEntity(size)

fun SizeEntity.toModel(): Size =
    Size(size)

fun List<SizeResponse>.toEntities(): List<SizeEntity> =
    this.map {
        it.toEntity()
    }

fun List<SizeEntity>.toModels(): List<Size> =
    this.map {
        it.toModel()
    }

fun Flow<List<SizeEntity>>.toFlowModels(): Flow<List<Size>> =
    this.map {
        it.toModels()
    }
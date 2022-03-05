package com.idmdragon.data.mapper

import com.idmdragon.data.source.local.entity.FisheryEntity
import com.idmdragon.data.source.remote.response.FisheryResponse
import com.idmdragon.domain.model.Fishery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun FisheryResponse.toEntity(): FisheryEntity =
    FisheryEntity(
       area_kota, area_provinsi, komoditas, price, size, tgl_parsed, timestamp, uuid
    )

fun FisheryEntity.toModel(): Fishery =
    Fishery(
        area_kota, area_provinsi, komoditas, price, size, tgl_parsed, timestamp, uuid
    )

fun List<FisheryResponse>.toEntities(): List<FisheryEntity> =
    this.map {
        it.toEntity()
    }

fun List<FisheryEntity>.toModels(): List<Fishery> =
    this.map {
        it.toModel()
    }

fun Flow<List<FisheryEntity>>.toFlowModels(): Flow<List<Fishery>> =
    this.map {
        it.toModels()
    }
package com.idmdragon.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FisheryEntity(
    val area_kota: String?,
    val area_provinsi: String?,
    val komoditas: String?,
    val price: String? ,
    val size: String?,
    val tgl_parsed: String?,
    val timestamp: String?,
    @PrimaryKey(autoGenerate = true)
    val uuid: Int = 0
)
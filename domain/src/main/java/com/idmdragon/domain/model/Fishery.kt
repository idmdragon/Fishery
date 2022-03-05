package com.idmdragon.domain.model

data class Fishery(
    val area_kota: String,
    val area_provinsi: String,
    val komoditas: String,
    val price: String?,
    val size: String?,
    val tgl_parsed: String,
    val timestamp: String,
    val uuid: String
)
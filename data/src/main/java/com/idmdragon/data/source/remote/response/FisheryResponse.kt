package com.idmdragon.data.source.remote.response

data class FisheryResponse(
    val area_kota: String= "",
    val area_provinsi: String= "",
    val komoditas: String= "",
    val price: String? = null,
    val size: String?= null,
    val tgl_parsed: String= "",
    val timestamp: String= "",
    val uuid: String= "",
)
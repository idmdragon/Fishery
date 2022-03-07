package com.idmdragon.data.source.remote.response

import com.idmdragon.domain.utils.ConverterHelper.convertMillisToString
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

data class FisheryBody(
    val uuid: String,
    val commodity: String,
    val areaProvince: String,
    val areaCity: String,
    val size: String,
    val price:String,
    val tgl_parsed: String,
    val timestamp: String
) {
    fun asRequestBody(): RequestBody {
        val multipartBodyBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
        multipartBodyBuilder.apply {
            addFormDataPart("uuid", uuid)
            addFormDataPart("komoditas", commodity)
            addFormDataPart("area_provinsi", areaProvince)
            addFormDataPart("area_kota", areaCity)
            addFormDataPart("size", size)
            addFormDataPart("price", size)
            addFormDataPart("tgl_parsed", tgl_parsed)
            addFormDataPart("timestamp", timestamp)
        }
        return multipartBodyBuilder.build()
    }

}

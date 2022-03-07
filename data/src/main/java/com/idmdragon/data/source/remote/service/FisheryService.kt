package com.idmdragon.data.source.remote.service

import com.idmdragon.data.source.remote.response.AreaResponse
import com.idmdragon.data.source.remote.response.FisheryResponse
import com.idmdragon.data.source.remote.response.PostResponse
import com.idmdragon.data.source.remote.response.SizeResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FisheryService {

    @GET("/v1/storages/{API_KEY}/list")
    suspend fun getListItem( @Path("API_KEY") apiKey: String): List<FisheryResponse>

    @POST("/v1/storages/{API_KEY}/list")
    suspend fun addListItem( @Path("API_KEY") apiKey: String, @Body fisheryBody: RequestBody): PostResponse

    @GET("/v1/storages/{API_KEY}/option_area")
    suspend fun getListAreaItem( @Path("API_KEY") apiKey: String): List<AreaResponse>

    @GET("/v1/storages/{API_KEY}/option_size")
    suspend fun getListSize( @Path("API_KEY") apiKey: String): List<SizeResponse>
}
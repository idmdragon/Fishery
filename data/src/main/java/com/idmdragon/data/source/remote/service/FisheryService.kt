package com.idmdragon.data.source.remote.service

import com.idmdragon.data.source.remote.response.AreaResponse
import com.idmdragon.data.source.remote.response.FisheryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FisheryService {

    @GET("/v1/storages/{API_KEY}/list")
    suspend fun getListItem( @Path("API_KEY") apiKey: String): List<FisheryResponse>

    @GET("/v1/storages/{API_KEY}/option_area")
    suspend fun getListAreaItem( @Path("API_KEY") apiKey: String): List<AreaResponse>

}
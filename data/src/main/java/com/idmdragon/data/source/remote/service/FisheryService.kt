package com.idmdragon.data.source.remote.service

import com.idmdragon.data.source.remote.response.FisheryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FisheryService {

    @GET("/v1/storages/{API_KEY}/list")
    suspend fun getListItem( @Path("API_KEY") apiKey: String): List<FisheryResponse>

}
package com.idmdragon.domain.usecase

import com.idmdragon.domain.model.Area
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.model.Size
import com.idmdragon.domain.repository.FisheryRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class FisheryUsecaseImpl(private val fisheryRepository: FisheryRepository): FisheryUsecase {

    override fun getAllFishery(query: String, queryArea: Area): Flow<Resource<List<Fishery>>> =
        fisheryRepository.getAllFishery(query,queryArea)

    override fun getAllArea(): Flow<Resource<List<Area>>> =
        fisheryRepository.getAllArea()

    override fun getAllSize(): Flow<Resource<List<Size>>> =
        fisheryRepository.getAllSize()

    override fun searchItem(query: String): Flow<Resource<List<Fishery>>> =
        fisheryRepository.searchItem(query)
}
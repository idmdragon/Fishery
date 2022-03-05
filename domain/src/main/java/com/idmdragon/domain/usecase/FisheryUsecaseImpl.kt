package com.idmdragon.domain.usecase

import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.repository.FisheryRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class FisheryUsecaseImpl(private val fisheryRepository: FisheryRepository): FisheryUsecase {

    override fun getAllFishery(): Flow<Resource<List<Fishery>>> =
        fisheryRepository.getAllFishery()

}
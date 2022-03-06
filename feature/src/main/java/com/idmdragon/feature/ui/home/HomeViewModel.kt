package com.idmdragon.feature.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.usecase.FisheryUsecase
import com.idmdragon.domain.utils.Resource

class HomeViewModel(private val useCase: FisheryUsecase) : ViewModel(){
    fun getAllFishery(): LiveData<Resource<List<Fishery>>> =
        useCase.getAllFishery().asLiveData()

    fun searchItem(query: String): LiveData<Resource<List<Fishery>>> =
        useCase.searchItem(query).asLiveData()
}
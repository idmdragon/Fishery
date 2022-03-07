package com.idmdragon.feature.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagedList
import com.idmdragon.domain.model.Area
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.usecase.FisheryUsecase
import com.idmdragon.domain.utils.Resource

class HomeViewModel(private val useCase: FisheryUsecase) : ViewModel(){

    fun getFilteredFishery(querySize: String, queryArea: Area): LiveData<Resource<List<Fishery>>> =
        useCase.getAllFishery(querySize,queryArea).asLiveData()

    fun getAllFishery(): LiveData<Resource<List<Fishery>>> =
        useCase.getAllFishery("",Area("","")).asLiveData()

    fun searchItem(query: String): LiveData<Resource<List<Fishery>>> =
        useCase.searchItem(query).asLiveData()
}
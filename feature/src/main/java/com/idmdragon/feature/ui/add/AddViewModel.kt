package com.idmdragon.feature.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idmdragon.domain.model.Area
import com.idmdragon.domain.model.Size
import com.idmdragon.domain.usecase.FisheryUsecase
import com.idmdragon.domain.utils.Resource

class AddViewModel(private val useCase: FisheryUsecase) : ViewModel(){

    private val listAreaProvince = MutableLiveData<List<String>>()
    private val listAreaCity = MutableLiveData<List<String>>()

    fun setAreaProvince(listItem: List<String>){
        listAreaProvince.value = listItem
    }

    fun setAreaCity(listItem: List<String>){
        listAreaCity.value = listItem
    }

    fun getAreaProvince(): LiveData<List<String>> = listAreaProvince

    fun getAreaCity(): LiveData<List<String>> = listAreaCity

    fun getAllArea(): LiveData<Resource<List<Area>>> =
        useCase.getAllArea().asLiveData()

    fun getAllSize(): LiveData<Resource<List<Size>>> =
        useCase.getAllSize().asLiveData()
}
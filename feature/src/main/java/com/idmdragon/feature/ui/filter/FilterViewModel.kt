package com.idmdragon.feature.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idmdragon.domain.model.Area
import com.idmdragon.domain.model.Size
import com.idmdragon.domain.usecase.FisheryUsecase
import com.idmdragon.domain.utils.Resource

class FilterViewModel (private val useCase: FisheryUsecase) : ViewModel(){

    var area : String = " , "
    var size : String = ""

    fun getAllArea(): LiveData<Resource<List<Area>>> =
        useCase.getAllArea().asLiveData()

    fun getAllSize(): LiveData<Resource<List<Size>>> =
        useCase.getAllSize().asLiveData()


}
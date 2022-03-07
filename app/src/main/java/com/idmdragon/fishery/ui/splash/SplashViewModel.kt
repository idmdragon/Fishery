package com.idmdragon.fishery.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idmdragon.domain.usecase.FisheryUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(private val useCase: FisheryUsecase) : ViewModel() {
    fun clearFishery() = viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            useCase.clearAllFisher()
        }
    }
}
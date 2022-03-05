package com.idmdragon.feature.di

import com.idmdragon.domain.usecase.FisheryUsecase
import com.idmdragon.domain.usecase.FisheryUsecaseImpl
import com.idmdragon.feature.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    factory<FisheryUsecase> {
        FisheryUsecaseImpl(get())
    }
    viewModel {
        HomeViewModel(get())
    }
}
package com.idmdragon.fishery.di

import com.idmdragon.domain.usecase.FisheryUsecase
import com.idmdragon.domain.usecase.FisheryUsecaseImpl
import com.idmdragon.fishery.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    factory<FisheryUsecase> {
        FisheryUsecaseImpl(get())
    }
    viewModel {
        SplashViewModel(get())
    }
}
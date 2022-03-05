package com.idmdragon.domain.di


import com.idmdragon.domain.usecase.FisheryUsecase
import com.idmdragon.domain.usecase.FisheryUsecaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<FisheryUsecase> {
        FisheryUsecaseImpl(get())
    }
}
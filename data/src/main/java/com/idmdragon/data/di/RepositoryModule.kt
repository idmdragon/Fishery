package com.idmdragon.data.di



import androidx.paging.ExperimentalPagingApi
import com.idmdragon.data.repository.FisheryRepositoryImpl
import com.idmdragon.domain.repository.FisheryRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<FisheryRepository> {
        FisheryRepositoryImpl(get(), get())
    }
}
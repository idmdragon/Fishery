package com.idmdragon.data.di


import com.idmdragon.data.BuildConfig
import com.idmdragon.data.source.remote.RemoteDataSource
import com.idmdragon.data.source.remote.service.FisheryService
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().apply {
    addInterceptor {
        val request: Request = it.request().newBuilder().build()
        it.proceed(request)
    }
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(httpClient.build())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val retrofitModule = module {
    single {
        retrofit.create(FisheryService::class.java)
    }

}

val remoteSourceModule = module {
    single {
        RemoteDataSource(get())
    }
}
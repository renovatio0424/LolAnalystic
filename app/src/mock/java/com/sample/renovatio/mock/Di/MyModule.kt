package com.sample.renovatio.mock.di

import com.sample.renovatio.mock.Api.RiotApiService
import com.sample.renovatio.mock.Main.MainContract
import com.sample.renovatio.mock.Main.MainPresenter
import com.sample.renovatio.mock.Model.MockSummonerRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var networkModule = module {
    single<RiotApiService> {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://kr.api.riotgames.com")
            .build()
            .create(RiotApiService::class.java)
    }
}

var mainModule = module {

    factory<MainContract.Repository> { MockSummonerRepositoryImpl() }
    factory<MainContract.Presenter> { (view: MainContract.View) -> MainPresenter(view, get()) }
}

var myDiModule = listOf(
    networkModule,
    mainModule
)
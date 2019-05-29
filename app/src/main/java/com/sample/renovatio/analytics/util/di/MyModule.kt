package com.sample.renovatio.analytics.util.di

import com.sample.renovatio.analytics.api.RiotApiService
import com.sample.renovatio.analytics.main.MainContract
import com.sample.renovatio.analytics.main.MainPresenter
import com.sample.renovatio.analytics.match_list.MatchListAdapter
import com.sample.renovatio.analytics.match_list.MatchListContract
import com.sample.renovatio.analytics.match_list.MatchListPresenter
import com.sample.renovatio.analytics.model.MatchListRepositoryImpl
import com.sample.renovatio.analytics.model.SummonerRepositoryImpl
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
    factory<MainContract.Repository> { SummonerRepositoryImpl(get()) }
    factory<MainContract.Presenter> { (view: MainContract.View) -> MainPresenter(view, get()) }
}

var matchListModule = module {
    factory<MatchListContract.Repository> { MatchListRepositoryImpl(get()) }
    factory { MatchListAdapter() }
    factory<MatchListContract.Presenter> { (view: MatchListContract.View, adapter: MatchListAdapter) -> MatchListPresenter(view, adapter, get()) }
}

var myDiModule = listOf(
    networkModule,
    mainModule,
    matchListModule
)
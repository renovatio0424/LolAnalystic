package com.sample.renovatio.lolanalystic.Di

import com.sample.renovatio.lolanalystic.Api.DataModel
import com.sample.renovatio.lolanalystic.ViewModel.MainViewModel
import com.sample.renovatio.lolanalystic.Api.DataModelImpl
import com.sample.renovatio.lolanalystic.Api.RiotApiService
import io.reactivex.Single
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitPart = module {
    single<RiotApiService> {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://kr.api.riotgames.com")
            .build()
            .create(RiotApiService::class.java)
    }
}

var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
}

var myDiModule = listOf(
    retrofitPart,
    modelPart,
    viewModelPart
)
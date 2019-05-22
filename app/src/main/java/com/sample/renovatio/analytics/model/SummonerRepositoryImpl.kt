package com.sample.renovatio.analytics.model

import com.sample.renovatio.analytics.api.RiotApiService
import com.sample.renovatio.analytics.main.MainContract
import com.sample.renovatio.analytics.model.DataModel.SummonerDTO
import io.reactivex.Single

class SummonerRepositoryImpl(private val service: RiotApiService) : MainContract.Repository {
    override fun getSummonerData(summonerName: String): Single<SummonerDTO> {
        return service.searchSummonerByName(summonerName)
    }
}
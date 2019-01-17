package com.sample.renovatio.lolanalystic.Api

import com.sample.renovatio.lolanalystic.Model.Summoner
import io.reactivex.Single

class DataModelImpl(private val service: RiotApiService) : DataModel {
    override fun getSummonerData(summonerName: String): Single<Summoner> {
        return service.searchSummonerByName(summonerName)
    }
}
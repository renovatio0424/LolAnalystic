package com.sample.renovatio.lolanalystic.Api

import com.sample.renovatio.lolanalystic.Model.Summoner
import io.reactivex.Single

interface DataModel {
    fun getSummonerData(summonerName: String) : Single<Summoner>
}
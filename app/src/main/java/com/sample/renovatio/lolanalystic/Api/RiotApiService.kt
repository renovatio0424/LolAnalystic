package com.sample.renovatio.lolanalystic.Api

import com.sample.renovatio.lolanalystic.Model.Summoner
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApiService {
    companion object {
        const val RIOT_API_KEY = "RGAPI-8e8545dd-0271-4963-a4fc-db94dab1b177"
    }

    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}?api_key=$RIOT_API_KEY")
    fun searchSummonerByName(
        @Path("summonerName") name: String
    ): Single<Summoner>
}
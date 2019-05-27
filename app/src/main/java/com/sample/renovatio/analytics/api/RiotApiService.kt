package com.sample.renovatio.analytics.api

import com.sample.renovatio.analytics.model.DataModel.MatchListDTO
import com.sample.renovatio.analytics.model.DataModel.SummonerDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApiService {
    companion object {
        const val RIOT_API_KEY = "RGAPI-71debac4-4e4c-4daa-8356-4a52811b63e2"
    }

    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}?api_key=$RIOT_API_KEY")
    fun searchSummonerByName(
        @Path("summonerName") name: String
    ): Single<SummonerDTO>

    @GET("/lol/match/v4/matchlists/by-account/{accountId}?api_key=$RIOT_API_KEY")
    fun searchMatchListByAccountID(
        @Path("accountId") accountId: String
    ): Single<MatchListDTO>
}
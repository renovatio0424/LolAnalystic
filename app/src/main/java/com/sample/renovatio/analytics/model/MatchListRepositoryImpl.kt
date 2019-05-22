package com.sample.renovatio.analytics.model

import com.sample.renovatio.analytics.api.RiotApiService
import com.sample.renovatio.analytics.match_list.MatchListContract
import com.sample.renovatio.analytics.model.DataModel.MatchListDTO
import io.reactivex.Single

class MatchListRepositoryImpl(private val service: RiotApiService) : MatchListContract.Repository {
    override fun getMatchList(accountId: String): Single<MatchListDTO> {
        return service.searchMatchListByAccountID(accountId)
    }
}
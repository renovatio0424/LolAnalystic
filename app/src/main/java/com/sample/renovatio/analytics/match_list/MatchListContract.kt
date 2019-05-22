package com.sample.renovatio.analytics.match_list

import com.sample.renovatio.analytics.model.DataModel.MatchListDTO
import com.sample.renovatio.analytics.model.DataModel.SummonerDTO
import io.reactivex.Single

interface MatchListContract {
    interface View {

    }

    interface Presenter {
        fun searchMatchList(summonerDTO: SummonerDTO)
    }

    interface Repository {
        fun getMatchList(accountId: String): Single<MatchListDTO>
    }
}
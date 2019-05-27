package com.sample.renovatio.analytics.match_list

import com.sample.renovatio.analytics.model.DataModel.MatchListDTO
import io.reactivex.Single

interface MatchListContract {
    interface View {
        fun setMatchListView(matchListDTO: MatchListDTO)
    }

    interface Presenter {
        fun searchMatchList(accountId: String)
        fun unsubscribe()
    }

    interface Repository {
        fun getMatchList(accountId: String): Single<MatchListDTO>
    }
}
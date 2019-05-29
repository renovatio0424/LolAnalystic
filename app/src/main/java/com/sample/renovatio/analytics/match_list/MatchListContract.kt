package com.sample.renovatio.analytics.match_list

import com.sample.renovatio.analytics.model.DataModel.MatchListDTO
import com.sample.renovatio.analytics.model.DataModel.MatchReferenceDTO
import io.reactivex.Single

interface MatchListContract {
    interface View {
    }

    interface Presenter {
        fun searchMatchList(accountId: String)
        fun unsubscribe()
    }

    interface Repository {
        fun getMatchList(accountId: String): Single<MatchListDTO>
    }

    interface Adapter {
        interface View {
            fun initView(viewHolder: MatchListAdapter.ViewHolder, matchReferenceDTO: MatchReferenceDTO)
            fun refresh()
        }

        interface Model {
            fun addItems(matchListDTO: MatchListDTO)
            fun clearItems()
        }
    }
}
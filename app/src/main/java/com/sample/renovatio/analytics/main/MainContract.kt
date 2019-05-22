package com.sample.renovatio.analytics.main

import com.sample.renovatio.analytics.model.DataModel.SummonerDTO
import io.reactivex.Single

class MainContract {

    interface View {
        fun showLoadingDialog()
        fun dismissLoadingDialog()
        fun showErrorMessage(errorCode: Int)
        fun showSummonerDataOnResultText(summonerData: SummonerDTO)
    }

    interface Presenter {
        fun getSummonerData(name: String)
        fun unsubscribe()
    }

    interface Repository {
        fun getSummonerData(summonerName: String): Single<SummonerDTO>
    }
}
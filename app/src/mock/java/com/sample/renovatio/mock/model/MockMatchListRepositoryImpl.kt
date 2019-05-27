package com.sample.renovatio.mock.model

import com.sample.renovatio.analytics.match_list.MatchListContract
import com.sample.renovatio.analytics.model.DataModel.MatchListDTO
import io.reactivex.Single


class MockMatchListRepositoryImpl: MatchListContract.Repository {

    private val mockMatchList = MatchListDTO.createMockDTO()

    override fun getMatchList(accountId: String): Single<MatchListDTO> {
        return Single.just(mockMatchList)
    }
}
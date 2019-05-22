package com.sample.renovatio.mock.model

import com.sample.renovatio.mock.Main.MainContract
import com.sample.renovatio.mock.Model.DataModel.SummonerDTO
import io.reactivex.Single
import io.reactivex.Single.error
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response.error

class MockSummonerRepositoryImpl : MainContract.Repository {
    private val mockSummonerDTO = SummonerDTO(
        accountId = "1",
        id = "id",
        name = "Test Account",
        profileIconId = 1,
        puuid = "1",
        revisionDate = 123456789L,
        summonerLevel = 1
    )

    override fun getSummonerData(summonerName: String): Single<SummonerDTO> {
        return when (summonerName) {
            mockSummonerDTO.name -> Single.just(mockSummonerDTO)
            "400BadRequest" -> error(
                HttpException(
                    error<SummonerDTO>(
                        400, ResponseBody.create(
                            MediaType.get("application/json"),
                            "error: Bad Request"
                        )
                    )
                )
            )
            else/*"404Error"*/ -> error(
                HttpException(
                    error<SummonerDTO>(
                        404, ResponseBody.create(
                            MediaType.get("application/json"),
                            "{\n" +
                                    "    \"status\": {\n" +
                                    "        \"status_code\": 404,\n" +
                                    "        \"message\": \"Data not found - summoner not found\"\n" +
                                    "    }\n" +
                                    "}"
                        )
                    )
                )
            )

        }
    }
}

package com.sample.renovatio.analytics.Main

import com.sample.renovatio.analytics.main.MainContract
import com.sample.renovatio.analytics.main.MainPresenter
import com.sample.renovatio.analytics.model.DataModel.SummonerDTO
import com.sample.renovatio.mock.model.MockSummonerRepositoryImpl
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.mockito.Mockito
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit


class MainPresenterTest : KoinTest {

    private lateinit var mockActivity: MainContract.View
    private lateinit var mockPresenter: MainPresenter
    private lateinit var mockRepository: MainContract.Repository
    private val mockSummonerData = SummonerDTO(
        accountId = "1",
        id = "id",
        name = "Test Account",
        profileIconId = 1,
        puuid = "1",
        revisionDate = 123456789L,
        summonerLevel = 1
    )

    @Before
    fun setUp() {
        mockActivity = Mockito.mock(MainContract.View::class.java)
        mockRepository = MockSummonerRepositoryImpl()
        mockPresenter = MainPresenter(mockActivity, mockRepository)

        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(object : ScheduledThreadPoolExecutor(1) {
                    override fun execute(runnable: Runnable) {
                        runnable.run()
                    }
                })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getSummonerData_SuccessCase() {
        // given
        // 1. presenter 에서 getSummonerData 요청시 가져올 데이터 세팅
        Mockito.`when`(mockRepository.getSummonerData(mockSummonerData.name))
            .thenReturn(Single.just(mockSummonerData))

        // 2. Android Main Thread 에서 요청할 수 없기 때문에 TestSubscriber 생성
        val testSubscriber:TestSubscriber<SummonerDTO> = TestSubscriber()

        // 3. TestSubscriber 넣기
        mockRepository.getSummonerData(mockSummonerData.name).toFlowable().subscribe(testSubscriber)

        // when
        // 4. getSummonerData 요청
        mockPresenter.getSummonerData(mockSummonerData.name)

        // then
        // 5. showSummonerDataOnResultText 호출하는가?
        Mockito.verify(mockActivity, Mockito.times(1)).showSummonerDataOnResultText(mockSummonerData)
    }

    @Test
    fun getSummonerData_FailCase(){
        //given
        val noExistSummonerName = "404Error"
//        Mockito.`when`(mockRepository.getSummonerData(noExistSummonerName))
//            .thenReturn(Single.error(Throwable("Data not found - summoner not found")))

        val testSubscriber:TestSubscriber<SummonerDTO> = TestSubscriber()

        mockRepository.getSummonerData(noExistSummonerName).toFlowable().subscribe(testSubscriber)
        //when
        mockPresenter.getSummonerData(noExistSummonerName)
        //then
        Mockito.verify(mockActivity, Mockito.times(1)).showErrorMessage(404)
    }

    @Test
    fun getSummonerData_EmptyCase(){
        //given
        val emptySummonerName = "400BadRequest"
        val testSubscriber:TestSubscriber<SummonerDTO> = TestSubscriber()

        mockRepository.getSummonerData(emptySummonerName).toFlowable().subscribe(testSubscriber)
        //when
        mockPresenter.getSummonerData(emptySummonerName)
        //then
        Mockito.verify(mockActivity, Mockito.times(1)).showErrorMessage(400)
    }
}
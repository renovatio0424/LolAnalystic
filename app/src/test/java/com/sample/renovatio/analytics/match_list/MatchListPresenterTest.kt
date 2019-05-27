package com.sample.renovatio.analytics.match_list

import com.sample.renovatio.analytics.model.DataModel.MatchListDTO
import com.sample.renovatio.mock.model.MockMatchListRepositoryImpl
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MatchListPresenterTest {

    private lateinit var mockActivity: MatchListContract.View
    private lateinit var mockPresenter: MatchListPresenter
    private lateinit var mockRepository: MatchListContract.Repository
    private val mockMatchListDTO: MatchListDTO = MatchListDTO.createMockDTO()

    @Before
    fun setUp() {
        mockActivity = Mockito.mock(MatchListContract.View::class.java)
        mockRepository = MockMatchListRepositoryImpl()
        mockPresenter = MatchListPresenter(mockActivity, mockRepository)

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

    @Test
    fun searchMatchList_success_case() {
        //given
        val accountId = "My Account Id"

        //when
        mockPresenter.searchMatchList(accountId)

        //then
        Mockito.verify(mockActivity, Mockito.times(1)).setMatchListView(mockMatchListDTO)
    }
}
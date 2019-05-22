package com.sample.renovatio.analytics.main


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class MainPresenter(private val view: MainContract.View, private val repositoryImpl: MainContract.Repository) :
    MainContract.Presenter {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun unsubscribe() {
        if (compositeDisposable.size() > 0)
            compositeDisposable.clear()
    }

    override fun getSummonerData(name: String) {

        view.showLoadingDialog()

        val disposable = repositoryImpl.getSummonerData(name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                view.dismissLoadingDialog()
                view.showSummonerDataOnResultText(it)
            },{
                view.dismissLoadingDialog()
                val errorCode = when(it){
                    is HttpException -> it.code()
                    else -> throw it
                }

                view.showErrorMessage(errorCode)
            })

        compositeDisposable.add(disposable)
    }
}
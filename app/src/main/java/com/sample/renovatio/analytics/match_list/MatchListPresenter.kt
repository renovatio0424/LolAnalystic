package com.sample.renovatio.analytics.match_list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MatchListPresenter(val view: MatchListContract.View, val repository: MatchListContract.Repository) : MatchListContract.Presenter {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun unsubscribe() {
        if (compositeDisposable.size() > 0)
            compositeDisposable.clear()
    }

    override fun searchMatchList(accountId: String) {
        val disposable = repository.getMatchList(accountId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                view.setMatchListView(it)
            },{
                TODO("not implemented")
            })

        compositeDisposable.add(disposable)
    }
}
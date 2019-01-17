package com.sample.renovatio.lolanalystic.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.renovatio.lolanalystic.Api.DataModel
import com.sample.renovatio.lolanalystic.Base.BaseKotlinViewModel
import com.sample.renovatio.lolanalystic.Model.Summoner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val model: DataModel) : BaseKotlinViewModel() {
    private val TAG = MainViewModel::class.java.simpleName

    private val _summonerLiveData = MutableLiveData<Summoner>()

    val summonerLiveData: LiveData<Summoner>
        get() = _summonerLiveData

    fun getSummonerData(name: String) {
        addDisposable(model.getSummonerData(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "response success] ${it.name} ${it.summonerLevel}")
                _summonerLiveData.postValue(it)
            },{
                Log.d(TAG, "response error] ${it.message}")
            })
        )
    }
}


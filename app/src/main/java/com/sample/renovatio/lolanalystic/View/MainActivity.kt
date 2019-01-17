package com.sample.renovatio.lolanalystic.View

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.Observer
import com.sample.renovatio.lolanalystic.Base.BaseKotlinActivity
import com.sample.renovatio.lolanalystic.R
import com.sample.renovatio.lolanalystic.ViewModel.MainViewModel
import com.sample.renovatio.lolanalystic.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseKotlinActivity<ActivityMainBinding, MainViewModel>() {
    private val TAG = MainActivity::class.java.simpleName

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {
        Log.d(TAG, "initStartView")
    }

    @SuppressLint("SetTextI18n")
    override fun initDataBinding() {
        Log.d(TAG, "initDataBinding")
        viewModel.summonerLiveData.observe(this, Observer {
            main_activity_search_text_view.text = "Summoner level : ${it.summonerLevel}"
        })
    }

    override fun initAfterBinding() {
        Log.d(TAG, "initAfterBinding")
        main_activity_search_button.setOnClickListener {
            Log.d(TAG, "click search button")
            viewModel.getSummonerData(main_activity_search_editext_view.text.toString())
        }
    }

}

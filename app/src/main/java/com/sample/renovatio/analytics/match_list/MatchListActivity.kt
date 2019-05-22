package com.sample.renovatio.analytics.match_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.renovatio.analytics.R

class MatchListActivity : AppCompatActivity(), MatchListContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matchlist)
    }
}

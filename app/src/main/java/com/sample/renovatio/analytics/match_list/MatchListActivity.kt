package com.sample.renovatio.analytics.match_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.renovatio.analytics.R
import com.sample.renovatio.analytics.model.DataModel.MatchListDTO
import kotlinx.android.synthetic.main.activity_matchlist.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MatchListActivity : AppCompatActivity(), MatchListContract.View {
    private val presenter: MatchListContract.Presenter by inject { parametersOf(this) }

    private lateinit var accountId: String

    override fun setMatchListView(matchListDTO: MatchListDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matchlist)
        accountId = intent.getStringExtra(ACCOUNT_ID)
        presenter.searchMatchList(accountId)

        initMatchList()
    }

    private fun initMatchList() {
//        TODO
//        rv_match_list.adapter =
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    companion object {
        const val ACCOUNT_ID = "ACCOUNT_ID"
        fun show(context: Context, accountId: String) {
            val intent = Intent(context, MatchListActivity::class.java)
            intent.putExtra(ACCOUNT_ID, accountId)
            context.startActivity(intent)
        }
    }
}

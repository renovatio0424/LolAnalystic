package com.sample.renovatio.analytics.match_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.renovatio.analytics.R
import com.sample.renovatio.analytics.model.DataModel.MatchListDTO
import kotlinx.android.synthetic.main.activity_matchlist.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


/**
 *  <Flow>
 *  View    --> presenter -> Model
 *  Adapter /
 * */
class MatchListActivity : AppCompatActivity(), MatchListContract.View {
    private val adapter: MatchListAdapter by inject()
    private val presenter: MatchListContract.Presenter by inject { parametersOf(this, adapter) }

    private lateinit var accountId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matchlist)
        accountId = intent.getStringExtra(ACCOUNT_ID)
        presenter.searchMatchList(accountId)

        initMatchList()
    }

    private fun initMatchList() {
        rv_match_list.layoutManager = LinearLayoutManager(this)
        rv_match_list.adapter = /*MatchListAdapter()*/adapter
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

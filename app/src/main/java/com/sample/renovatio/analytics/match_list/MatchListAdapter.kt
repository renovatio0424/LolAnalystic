package com.sample.renovatio.analytics.match_list

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.renovatio.analytics.model.DataModel.MatchListDTO
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.sample.renovatio.analytics.R
import com.sample.renovatio.analytics.model.DataModel.MatchReferenceDTO
import kotlinx.android.synthetic.main.item_match_list.view.*


class MatchListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), MatchListContract.Adapter.View, MatchListContract.Adapter.Model {
    private var matchListDTO: MatchListDTO? = null

    //View
    override fun initView(viewHolder: ViewHolder, matchReferenceDTO: MatchReferenceDTO) {
        viewHolder.tvResult.text = matchReferenceDTO.toString()
    }

    override fun refresh() {
        notifyDataSetChanged()
    }

    //Model
    override fun addItems(matchListDTO: MatchListDTO) {
        this.matchListDTO = matchListDTO
        refresh()
    }

    override fun clearItems() {
        if(matchListDTO != null)
            matchListDTO!!.matches.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.item_match_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (matchListDTO == null) 0 else matchListDTO!!.matches.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (matchListDTO == null) return

        initView(holder as ViewHolder, matchListDTO!!.matches[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvResult: TextView = view.item_match_list_result
    }
}
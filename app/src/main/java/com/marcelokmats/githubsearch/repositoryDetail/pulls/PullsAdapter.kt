package com.marcelokmats.githubsearch.repositoryDetail.pulls

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Pull
import com.marcelokmats.githubsearch.repositoryDetail.DetailListView
import kotlinx.android.synthetic.main.list_item.view.*

class PullsAdapter(private val items : List<Pull>, private val context: Context, private val view: DetailListView) :
    RecyclerView.Adapter<PullsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = items[position].title
        holder.txtSecondayText.text = items[position].number.toString()

        holder.root.setOnClickListener { view.onPullClick(items[position]) }
    }


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val root = view.root
        val txtName = view.txtName
        val txtSecondayText = view.txtSecondayText
    }
}
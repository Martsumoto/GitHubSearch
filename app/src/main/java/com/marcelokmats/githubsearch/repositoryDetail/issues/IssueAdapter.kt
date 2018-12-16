package com.marcelokmats.githubsearch.repositoryDetail.issues

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Issue
import kotlinx.android.synthetic.main.list_item.view.*

class IssueAdapter (val items : List<Issue>, val context: Context) :
    RecyclerView.Adapter<IssueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: IssueAdapter.ViewHolder, position: Int) {
        holder.txtName.text = items.get(position).title
        holder.txtSecondayText.text = items.get(position).number.toString()

        //holder.root.setOnClickListener { view.onRepositoryClick(items.get(position)) }
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val root = view.root
        val txtName = view.txtName
        val txtSecondayText = view.txtSecondayText
    }
}
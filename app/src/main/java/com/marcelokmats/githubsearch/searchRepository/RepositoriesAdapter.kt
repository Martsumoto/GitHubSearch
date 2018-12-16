package com.marcelokmats.githubsearch.searchRepository

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Repository
import kotlinx.android.synthetic.main.repository_list_item.view.*

class RepositoriesAdapter(val items : List<Repository>, val context: Context, val view: SearchActivityView) :
    RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.repository_list_item, parent, false));
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = items.get(position).name

        holder.root.setOnClickListener { view.onRepositoryClick(items.get(position)) }
    }


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val root = view.root
        val txtName = view.txtName
    }
}
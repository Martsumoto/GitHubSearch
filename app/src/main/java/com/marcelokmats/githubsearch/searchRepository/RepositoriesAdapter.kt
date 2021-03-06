package com.marcelokmats.githubsearch.searchRepository

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Repository
import kotlinx.android.synthetic.main.list_item.view.*

class RepositoriesAdapter(private val items : List<Repository>, private val context: Context, private val view: SearchView) :
    RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name : String = items[position].id.toString() + " - " + items[position].name
        holder.txtName.text = name
        holder.txtSecondayText.text = items[position].description

        holder.root.setOnClickListener { view.onRepositoryClick(items[position]) }
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val root = view.root
        val txtName = view.txtName
        val txtSecondayText = view.txtSecondayText
    }
}
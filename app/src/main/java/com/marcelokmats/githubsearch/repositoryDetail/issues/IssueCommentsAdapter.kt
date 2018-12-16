package com.marcelokmats.githubsearch.repositoryDetail.issues

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.IssueComment
import com.marcelokmats.githubsearch.util.ImageUtil
import kotlinx.android.synthetic.main.comment_list_item.view.*

class IssueCommentsAdapter (private val items : List<IssueComment>, private val context: Context) :
    RecyclerView.Adapter<IssueCommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_list_item, parent, false));
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: IssueCommentsAdapter.ViewHolder, position: Int) {
        ImageUtil.setupImage(context, items[position].user.avatarUrl, holder.imgAvatar)
        holder.txtName.text = items[position].user.login
        holder.txtComment.text = items[position].body
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val root = view.root
        val imgAvatar = view.imgAvatar
        val txtName = view.txtUserName
        val txtComment = view.txtComment
    }
}
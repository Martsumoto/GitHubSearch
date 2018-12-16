package com.marcelokmats.githubsearch.repositoryDetail.pulls

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Pull
import com.marcelokmats.githubsearch.repositoryDetail.DetailViewModel
import com.marcelokmats.githubsearch.util.ViewUtil
import kotlinx.android.synthetic.main.list_fragment.*

class PullsFragment : Fragment() {

    private var detailViewModel : DetailViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProviders.of(activity!!).get(DetailViewModel::class.java)

        detailViewModel?.fetchPulls(detailViewModel?.repository?.user?.login ?: "",
            detailViewModel?.repository?.name ?: "")?.observe(this,
            Observer { value ->
                populateList(value)
            })
    }

    fun populateList(pull: List<Pull>?) {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = PullsAdapter(pull ?: emptyList(), this.context!!)

        toggleVisibility(ViewUtil.Type.CONTENT)
    }

    fun toggleVisibility(type : ViewUtil.Type) {
        ViewUtil.toggleVisibility(recyclerView, progressBar, txtEmptyList, type);
    }
}
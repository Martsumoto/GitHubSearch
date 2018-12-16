package com.marcelokmats.githubsearch.repositoryDetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Issue
import com.marcelokmats.githubsearch.model.Pull
import com.marcelokmats.githubsearch.repositoryDetail.issues.IssueAdapter
import com.marcelokmats.githubsearch.repositoryDetail.pulls.PullsAdapter
import com.marcelokmats.githubsearch.util.ViewUtil
import kotlinx.android.synthetic.main.list_fragment.*

class DetailListFragment : Fragment() {

    companion object {
        fun newInstance(type: DetailActivity.Type): DetailListFragment {
            val fragment = DetailListFragment()
            val args = Bundle()
            args.putInt(DetailActivity.FRAGMENT_TYPE, type.ordinal)
            fragment.arguments = args
            return fragment
        }
    }

    private val mType by lazy {
        arguments?.get(DetailActivity.FRAGMENT_TYPE)
    }

    private var detailViewModel : DetailViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProviders.of(activity!!).get(DetailViewModel::class.java)

        fetchValues()
    }

    fun populateIssueList(issueList: List<Issue>?) {
        val sortedList = issueList?.sortedWith(compareBy(Issue::number))
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = IssueAdapter(sortedList ?: emptyList(),
            this.context!!
        )

        toggleVisibility(ViewUtil.Type.CONTENT)
    }

    fun populatePullList(pullList: List<Pull>?) {
        val sortedList = pullList?.sortedWith(compareBy(Pull::number))
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = PullsAdapter(sortedList ?: emptyList(), this.context!!)

        toggleVisibility(ViewUtil.Type.CONTENT)
    }

    fun toggleVisibility(type : ViewUtil.Type) {
        ViewUtil.toggleVisibility(recyclerView, progressBar, txtEmptyList, type);
    }

    fun fetchValues() {
        toggleVisibility(ViewUtil.Type.PROGRESSBAR)

        if (mType == DetailActivity.Type.ISSUE) {
            detailViewModel?.fetchIssues(
                detailViewModel?.repository?.user?.login ?: "",
                detailViewModel?.repository?.name ?: "")
                ?.observe(this, Observer { value -> populateIssueList(value) })
        } else {
            detailViewModel?.fetchPulls(detailViewModel?.repository?.user?.login ?: "",
                detailViewModel?.repository?.name ?: "")
                ?.observe(this, Observer { value -> populatePullList(value) })
        }
    }
}
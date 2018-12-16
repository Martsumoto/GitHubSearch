package com.marcelokmats.githubsearch.repositoryDetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
import com.marcelokmats.githubsearch.repositoryDetail.issues.IssueDetailActivity
import com.marcelokmats.githubsearch.repositoryDetail.pulls.PullDetailActivity
import com.marcelokmats.githubsearch.repositoryDetail.pulls.PullsAdapter
import com.marcelokmats.githubsearch.searchRepository.SearchActivity
import com.marcelokmats.githubsearch.util.ViewUtil
import kotlinx.android.synthetic.main.list_fragment.*

class DetailListFragment : Fragment(), DetailListView {

    companion object {
        fun newInstance(type: DetailListActivity.Type): DetailListFragment {
            val fragment = DetailListFragment()
            val args = Bundle()
            args.putInt(DetailListActivity.FRAGMENT_TYPE, type.ordinal)
            fragment.arguments = args
            return fragment
        }
    }

    private val mType by lazy {
        DetailListActivity.Type.values()[arguments?.get(DetailListActivity.FRAGMENT_TYPE) as Int]
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
    override fun onPullClick(pull: Pull) {
        val intent = Intent(this.activity, PullDetailActivity::class.java)
        intent.putExtra(DetailListActivity.PULL, pull)
        intent.putExtra(SearchActivity.REPOSITORY, detailViewModel?.repository)
        this.startActivity(intent)
    }

    override fun onIssueClick(issue: Issue) {
        val intent = Intent(this.activity, IssueDetailActivity::class.java)
        intent.putExtra(DetailListActivity.ISSUE, issue)
        intent.putExtra(SearchActivity.REPOSITORY, detailViewModel?.repository)
        this.startActivity(intent)
    }

    private fun populateIssueList(issueList: List<Issue>?) {
        if (issueList?.size ?: 0 > 0) {
            val sortedList = issueList?.sortedWith(compareBy(Issue::number))
            recyclerView.layoutManager = LinearLayoutManager(this.context)
            recyclerView.adapter = IssueAdapter(sortedList ?: emptyList(), this.context!!, this)
            toggleVisibility(ViewUtil.Type.CONTENT)
        } else {
            if (issueList == null) {
                txtEmptyList.text = getString(R.string.issues_load_error)
            } else {
                txtEmptyList.text = getString(R.string.no_issues)
            }
            toggleVisibility(ViewUtil.Type.ERROR)
        }
    }

    private fun populatePullList(pullList: List<Pull>?) {
        if (pullList?.size ?: 0 > 0) {
            val sortedList = pullList?.sortedWith(compareBy(Pull::number))
            recyclerView.layoutManager = LinearLayoutManager(this.context)
            recyclerView.adapter = PullsAdapter(sortedList ?: emptyList(), this.context!!, this)
            toggleVisibility(ViewUtil.Type.CONTENT)
        } else {
            if (pullList == null) {
                txtEmptyList.text = getString(R.string.pull_load_error)
            } else {
                txtEmptyList.text = getString(R.string.no_pulls)
            }
            toggleVisibility(ViewUtil.Type.ERROR)
        }
    }

    private fun toggleVisibility(type : ViewUtil.Type) {
        ViewUtil.toggleVisibility(recyclerView, progressBar, txtEmptyList, type);
    }

    private fun fetchValues() {
        toggleVisibility(ViewUtil.Type.PROGRESSBAR)

        if (mType == DetailListActivity.Type.ISSUE) {
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
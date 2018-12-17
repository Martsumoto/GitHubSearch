package com.marcelokmats.githubsearch.repositoryDetail.issues

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Issue
import com.marcelokmats.githubsearch.model.IssueComment
import com.marcelokmats.githubsearch.repositoryDetail.DetailListActivity
import com.marcelokmats.githubsearch.searchRepository.SearchActivity
import com.marcelokmats.githubsearch.util.ImageUtil
import com.marcelokmats.githubsearch.util.ViewUtil
import kotlinx.android.synthetic.main.issue_detail_activity.*

class IssueDetailActivity  : AppCompatActivity() {

    companion object {
        const val ISSUE = "ISSUE"
    }

    lateinit var mIssue : Issue

    private val mViewModel by lazy {
        ViewModelProviders.of(this,
            IssueViewModelFactory(intent.getParcelableExtra(SearchActivity.REPOSITORY),
                intent.getParcelableExtra(DetailListActivity.ISSUE)))
            .get(IssueDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.issue_detail_activity)

        loadIntent()
        populateViews()
        fetchComments()
    }

    private fun loadIntent() {
        mIssue = intent.getParcelableExtra(DetailListActivity.ISSUE)
    }

    private fun populateViews() {
        ImageUtil.setupImage(this, mIssue.user.avatarUrl, imgAvatar)
        txtTitle.text = mIssue.user.login
        txtDescription.text = mIssue.body
        labelReadMore.setOnClickListener {
            toggleIssueDescriptionVisibility()
             }

        supportActionBar?.title = getString(R.string.issue_detail_title)
        supportActionBar?.subtitle = mIssue.number.toString() + " - " + mIssue.title
    }

    private fun fetchComments() {
        toggleVisibility(ViewUtil.Type.PROGRESSBAR)

        mViewModel.fetchIssueComments(
            mViewModel.repository.user.login,
            mViewModel.repository.name,
            mViewModel.issue.number).observe(this, Observer { value -> populateCommentList(value) })
    }

    private fun populateCommentList(commentList: List<IssueComment>?) {
        if (commentList?.size ?: 0 > 0) {
            val sortedList = commentList?.sortedWith(compareBy(IssueComment::id))
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = IssueCommentsAdapter(sortedList ?: emptyList(), this)
            toggleVisibility(ViewUtil.Type.CONTENT)
        } else {
            if (commentList == null) {
                txtNoComments.text = getString(R.string.comments_load_error)
            } else {
                txtNoComments.text = getString(R.string.no_comments)
            }
            toggleVisibility(ViewUtil.Type.ERROR)
        }
    }

    private fun toggleVisibility(type : ViewUtil.Type) {
        ViewUtil.toggleVisibility(recyclerView, progressBar, txtNoComments, type)
    }

    private fun toggleIssueDescriptionVisibility() {
        if (labelReadMore.text == this.getString(R.string.show_more_label)) {
            //ViewUtil.expand(txtDescription)
            labelReadMore.setText(R.string.show_less_label)
            txtDescription.maxLines = Int.MAX_VALUE
            txtDescription.movementMethod = ScrollingMovementMethod.getInstance()
            layoutComments.visibility = View.GONE
        } else {
            //ViewUtil.collapse(txtDescription)
            labelReadMore.setText(R.string.show_more_label)
            txtDescription.maxLines = 3
            txtDescription.movementMethod = null
            layoutComments.visibility = View.VISIBLE
        }
    }
}
package com.marcelokmats.githubsearch.repositoryDetail.pulls

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Pull
import com.marcelokmats.githubsearch.repositoryDetail.DetailListActivity
import kotlinx.android.synthetic.main.pull_detail_activity.*

class PullDetailActivity  : AppCompatActivity() {

    lateinit var mPull : Pull

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pull_detail_activity)

        loadIntent()
        populateViews()
    }

    fun loadIntent() {
        mPull = intent.getParcelableExtra(DetailListActivity.PULL)
    }

    fun populateViews() {
        if (mPull != null) {
            txtNumber.text = mPull.number.toString()
            txtTitle.text = mPull.title

            // TODO pull request detail screen

            supportActionBar?.title = getString(R.string.pull_detail_title)
        }
    }}
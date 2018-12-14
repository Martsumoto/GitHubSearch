package com.marcelokmats.githubsearch.searchRepository

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Repository
import com.marcelokmats.githubsearch.model.RepositorySearchResult
import kotlinx.android.synthetic.main.activity_main.*

class SearchActivity : AppCompatActivity(), SearchActivityView {

    lateinit var mSearchViewModel : SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        mSearchViewModel.searchRepositories("butterknife").observe(this,
            Observer { value -> updateRepoList(value)
            })

//        mSearchViewModel.repositoriesLivedata.observe(this, Observer { this::updateRepoList })
    }

    override fun onRepositoryClick() {
    }

    fun updateRepoList(searchResult : RepositorySearchResult?) {
        listRepositories.layoutManager = LinearLayoutManager(this)
        listRepositories.adapter = RepositoriesAdapter(searchResult?.repositories ?: emptyList(), this, this)
        var i : Int = searchResult?.count ?: 0
    }

    fun showRepositoryDetail(repository: Repository) {
        
    }
}

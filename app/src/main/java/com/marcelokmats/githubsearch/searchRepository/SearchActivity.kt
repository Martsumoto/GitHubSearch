package com.marcelokmats.githubsearch.searchRepository

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Repository
import com.marcelokmats.githubsearch.model.RepositorySearchResult
import com.marcelokmats.githubsearch.repositoryDetail.DetailActivity
import com.marcelokmats.githubsearch.util.ViewUtil
import kotlinx.android.synthetic.main.search_activity.*

class SearchActivity : AppCompatActivity(), SearchActivityView {

    private val mSearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

    }

    override fun onResume() {
        super.onResume()

        this.btnSearch.setOnClickListener { v -> searchRepository(edtSearchRepository.text.toString())}
    }

    override fun onRepositoryClick(repository: Repository) {
        if (repository != null) {
            showRepositoryDetail(repository)
        }
    }

    fun searchRepository(repository: String) {
        if (!TextUtils.isEmpty(repository)) {
            toggleVisibility(ViewUtil.Type.PROGRESSBAR)
            mSearchViewModel.searchRepositories(repository).observe(this,
                Observer { value ->
                    updateRepoList(value)
                })
        } else {
            updateRepoList(null)
        }
    }

    fun updateRepoList(searchResult : RepositorySearchResult?) {
        listRepositories.layoutManager = LinearLayoutManager(this)
        listRepositories.adapter = RepositoriesAdapter(searchResult?.repositories ?: emptyList(), this, this)

        toggleVisibility(ViewUtil.Type.CONTENT)
    }

    fun showRepositoryDetail(repository: Repository) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Companion.REPOSITORY, repository)
        this.startActivity(intent)
    }

    fun toggleVisibility(type : ViewUtil.Type) {
        ViewUtil.toggleVisibility(listRepositories, progressBar, txtEmptyList, type);
    }

    companion object {
        const val REPOSITORY = "REPOSITORY"
    }
}

package com.marcelokmats.githubsearch.searchRepository

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.model.Repository
import com.marcelokmats.githubsearch.model.RepositorySearchResult
import com.marcelokmats.githubsearch.repositoryDetail.DetailListActivity
import com.marcelokmats.githubsearch.util.ViewUtil
import kotlinx.android.synthetic.main.search_activity.*

class SearchActivity : AppCompatActivity(), SearchView {

    companion object {
        const val REPOSITORY = "REPOSITORY"
    }

    private val mSearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        edtSearchRepository.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                btnSearch.callOnClick()
                true
            } else {
                false
            }
        }
    }

    override fun onResume() {
        super.onResume()

        this.btnSearch.setOnClickListener { v -> searchRepository(edtSearchRepository.text.toString())}

        mSearchViewModel.getRepositoriesLiveData().observe(this,
            Observer { value ->
                updateRepoList(value)
            })
    }

    override fun onRepositoryClick(repository: Repository) {
        if (repository != null) {
            showRepositoryDetail(repository)
        }
    }

    fun searchRepository(repository: String) {
        if (!TextUtils.isEmpty(repository)) {
            toggleVisibility(ViewUtil.Type.PROGRESSBAR)

            mSearchViewModel.setSearchTextValue(repository)
        } else {
            updateRepoList(null)
        }
    }

    private fun updateRepoList(searchResult : RepositorySearchResult?) {
        if (searchResult?.count ?: 0 > 0) {
            listRepositories.layoutManager = LinearLayoutManager(this)
            listRepositories.adapter = RepositoriesAdapter(searchResult?.repositories ?: emptyList(), this, this)

            toggleVisibility(ViewUtil.Type.CONTENT)
        } else {
            if (searchResult == null) {
                txtEmptyList.text = getString(R.string.repository_load_error)
            } else {
                txtEmptyList.text = getString(R.string.no_repositories)
            }
            toggleVisibility(ViewUtil.Type.ERROR)
        }
    }

    private fun showRepositoryDetail(repository: Repository) {
        val intent = Intent(this, DetailListActivity::class.java)
        intent.putExtra(REPOSITORY, repository)
        this.startActivity(intent)
    }

    private fun toggleVisibility(type : ViewUtil.Type) {
        ViewUtil.toggleVisibility(listRepositories, progressBar, txtEmptyList, type);
    }
}

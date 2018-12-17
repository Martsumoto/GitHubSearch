package com.marcelokmats.githubsearch.repositoryDetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.searchRepository.SearchActivity
import kotlinx.android.synthetic.main.detail_list_activity.*

class DetailListActivity  : AppCompatActivity() {

    enum class Type{
        ISSUE,
        PULL
    }

    companion object {
        val FRAGMENT_TYPE = "FRAGMENT_TYPE"
        const val ISSUE = "ISSUE"
        const val PULL = "PULL"
    }

    private lateinit var mDetailViewModel : DetailViewModel

    var mSelectedFragment : Int = Type.ISSUE.ordinal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_list_activity)
        setupBottomNavigationView()

        if (savedInstanceState != null) {
            mSelectedFragment = savedInstanceState.getInt(FRAGMENT_TYPE)
        }
        mDetailViewModel =
                ViewModelProviders.of(this,
                    DetailViewModelFactory(intent.getParcelableExtra(SearchActivity.REPOSITORY)))
                    .get(DetailViewModel::class.java)
        addFragment(DetailListFragment.newInstance(Type.values()[mSelectedFragment]))

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(FRAGMENT_TYPE, mSelectedFragment)
    }

    override fun onResume() {
        super.onResume()

        supportActionBar?.title = mDetailViewModel.repository.name
        supportActionBar?.subtitle = mDetailViewModel.repository.id.toString()
    }

    fun setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_issues -> {
                    mSelectedFragment = Type.ISSUE.ordinal
                    val fragment = DetailListFragment.newInstance(Type.ISSUE)
                    addFragment(fragment)
                }
                R.id.action_pull_requests -> {
                    mSelectedFragment = Type.PULL.ordinal
                    val fragment = DetailListFragment.newInstance(Type.PULL)
                    addFragment(fragment)
                }
            }
            true
        }
    }

    private fun addFragment(fragment : Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}
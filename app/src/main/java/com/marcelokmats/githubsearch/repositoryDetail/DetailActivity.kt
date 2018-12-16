package com.marcelokmats.githubsearch.repositoryDetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.marcelokmats.githubsearch.R
import com.marcelokmats.githubsearch.repositoryDetail.issues.DetailListFragment
import com.marcelokmats.githubsearch.searchRepository.SearchActivity
import kotlinx.android.synthetic.main.detail_activity.*

class DetailActivity  : AppCompatActivity() {

    enum class Type{
        ISSUE,
        PULL
    }

    companion object {
        val FRAGMENT_TYPE = "FRAGMENT_TYPE"
    }

    private lateinit var mDetailViewModel : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        setupBottomNavigationView()
        mDetailViewModel =
                ViewModelProviders.of(this,
                    DetailViewModelFactory(intent.getParcelableExtra(SearchActivity.REPOSITORY)))
                    .get(DetailViewModel::class.java)
        addFragment(DetailListFragment())
    }


    fun setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_issues -> {
                    val fragment = DetailListFragment.newInstance(Type.ISSUE)
                    addFragment(fragment)
                }
                R.id.action_pull_requests -> {
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
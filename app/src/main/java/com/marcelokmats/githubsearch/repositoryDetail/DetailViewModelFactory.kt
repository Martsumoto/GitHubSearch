package com.marcelokmats.githubsearch.repositoryDetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcelokmats.githubsearch.model.Repository

class DetailViewModelFactory(val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }


}
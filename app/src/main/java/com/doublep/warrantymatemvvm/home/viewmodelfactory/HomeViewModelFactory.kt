package com.doublep.warrantymatemvvm.home.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doublep.warrantymatemvvm.home.viewmodel.HomeViewModel
import com.doublep.warrantymatemvvm.repo.WMRepository

class HomeViewModelFactory(val repo: WMRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repo) as T
    }
}
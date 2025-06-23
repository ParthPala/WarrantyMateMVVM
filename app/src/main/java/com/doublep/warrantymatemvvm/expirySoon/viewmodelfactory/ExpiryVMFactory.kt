package com.doublep.warrantymatemvvm.expirySoon.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doublep.warrantymatemvvm.expirySoon.viewmodel.ExpiryViewModel

import com.doublep.warrantymatemvvm.repo.WMRepository

class ExpiryVMFactory(val repo : WMRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpiryViewModel(repo) as T
    }
}
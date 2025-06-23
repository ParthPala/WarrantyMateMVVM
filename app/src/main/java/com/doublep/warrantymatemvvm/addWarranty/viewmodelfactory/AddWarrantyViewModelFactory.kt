package com.doublep.warrantymatemvvm.addWarranty.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doublep.warrantymatemvvm.addWarranty.viewmodel.AddWarrantyViewModel
import com.doublep.warrantymatemvvm.repo.WMRepository

class AddWarrantyViewModelFactory(val repo : WMRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddWarrantyViewModel(repo) as T
    }
}
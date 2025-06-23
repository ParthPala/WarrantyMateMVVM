package com.doublep.warrantymatemvvm.allWarranty.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doublep.warrantymatemvvm.allWarranty.viewmodel.AllWarrantyVM
import com.doublep.warrantymatemvvm.repo.WMRepository

class AllWarrantyVMFactory(val repo: WMRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllWarrantyVM(repo) as T
    }
}
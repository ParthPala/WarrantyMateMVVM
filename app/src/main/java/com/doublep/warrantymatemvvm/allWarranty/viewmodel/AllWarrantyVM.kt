package com.doublep.warrantymatemvvm.allWarranty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.doublep.warrantymatemvvm.db.data.HomeEntity
import com.doublep.warrantymatemvvm.repo.WMRepository

class AllWarrantyVM(val repo: WMRepository) : ViewModel() {

    fun getWarrantyDetails() : LiveData<List<HomeEntity>>{
        return repo.getWarrantyDetails()
    }
}
package com.doublep.warrantymatemvvm.expirySoon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.doublep.warrantymatemvvm.db.data.HomeEntity
import com.doublep.warrantymatemvvm.repo.WMRepository

class ExpiryViewModel(val repo: WMRepository) : ViewModel() {

    fun getWarrantyDetails(values: List<String>): LiveData<List<HomeEntity>> {
        val response = repo.getWarrantyDetails()
        val filteredList = response.map { list ->
            list.filter { it ->
                values.contains(it.itemExpiryDate)
            }
        }
        return filteredList
    }
}


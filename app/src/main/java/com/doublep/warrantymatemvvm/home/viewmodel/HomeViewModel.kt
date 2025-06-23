package com.doublep.warrantymatemvvm.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.doublep.warrantymatemvvm.db.data.HomeEntity
import com.doublep.warrantymatemvvm.repo.WMRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repo: WMRepository) : ViewModel() {


    var result: LiveData<Int> = MutableLiveData<Int>()
    var resultExp: LiveData<Int> = MutableLiveData<Int>()


    fun getWarrantyDetailsCount() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = repo.getWarrantyDetailsCount()
            result = resp
        }
    }

    fun getExpiryCount(values: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getWarrantyDetails()
            Log.i("LauncherX:HoVM", "==in result == ${result.value}")
            resultExp = result.map { list ->
                Log.i("LauncherX:HoVM", "==in filter list 1== $list")
                list.count { it ->
                    Log.i("LauncherX:HoVM", "==in filter list == $it")
                    values.contains(it.itemExpiryDate)
                }
            }
        }
    }

    fun getWarrantyDetails(): LiveData<List<HomeEntity>> {
        return repo.getWarrantyDetails()
    }

}
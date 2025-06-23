package com.doublep.warrantymatemvvm.addWarranty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doublep.warrantymatemvvm.db.data.HomeEntity
import com.doublep.warrantymatemvvm.repo.WMRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddWarrantyViewModel(val repo: WMRepository) : ViewModel() {

    private var _result = MutableLiveData<String>()
    var result: LiveData<String> = _result

    suspend fun addWarranty(
        itemName: String,
        itemType: String,
        itemExpiryDate: String,
        itemPic: Int
    ): String {
        val item = HomeEntity(0, itemName, itemExpiryDate, itemType, itemPic)
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertWarrantyDetails(item)
        }
        return "Insert successful"

    }

    fun checkEmpty(itemName: String, itemType: String, itemExpiryDate: String): Boolean {
        Log.i("LauncherX", "=== in check empty name == $itemName")
        Log.i("LauncherX", "=== in check empty type == $itemType")
        Log.i("LauncherX", "=== in check empty date == $itemExpiryDate")
        return itemName.isEmpty()
    }

    fun addDetails(itemName: String, itemType: String, itemExpiryDate: String, itemPic: Int) {
        viewModelScope.launch {
            var response = "Please check all the fields !!"
            Log.i(
                "LauncherX",
                "=== in check empty value == ${checkEmpty(itemName, itemType, itemExpiryDate)}"
            )
            if (!checkEmpty(itemName, itemType, itemExpiryDate)) {
                Log.i("LauncherX", "=== in check empty inside ==")
                response = addWarranty(itemName, itemType, itemExpiryDate, itemPic)
                _result.postValue(response)
            }
            _result.postValue(response)

        }
    }
}
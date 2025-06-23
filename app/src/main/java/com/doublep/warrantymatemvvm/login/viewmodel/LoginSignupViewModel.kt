package com.doublep.warrantymatemvvm.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doublep.warrantymatemvvm.db.data.LoginEntity
import com.doublep.warrantymatemvvm.repo.WMRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginSignupViewModel(val repo: WMRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<String>()
    val loginResult: LiveData<String> = _loginResult


    fun insertLoginDetails(loginEntity: LoginEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertLoginDetails(loginEntity)
        }
    }


    suspend fun doLogin(loginId: String, password: String): String {
        Log.i("LauncherX:VM", "=== in do login === ")
        var values = "Please enter username or password"
        if (!checkEmpty(loginId, password))
            return values

        Log.i("LauncherX:VM", "=== in if empty === ")
        val entityData = withContext(Dispatchers.IO) {
            repo.getLoginDetails(loginId, password)
        }
        return if (entityData != null) {
            Log.i("LauncherX:VM", "=== in login suc === ")
            "Login Successful"
        } else {
            Log.i("LauncherX:VM", "=== in login fail === ")
            "Couldn't Find Login Details, Please SignUp"
        }
    }

fun clickLogin(loginId: String, password: String) {
    viewModelScope.launch {
        val result = doLogin(loginId,password)
        _loginResult.postValue(result)
    }
}

fun checkEmpty(loginId: String, password: String): Boolean =
    !(loginId.isEmpty() || password.isEmpty())

}
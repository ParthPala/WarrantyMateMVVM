package com.doublep.warrantymatemvvm.login.vmfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doublep.warrantymatemvvm.login.viewmodel.LoginSignupViewModel
import com.doublep.warrantymatemvvm.repo.WMRepository

class LoginSignupVMFactory(val repo: WMRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginSignupViewModel(repo) as T
    }
}
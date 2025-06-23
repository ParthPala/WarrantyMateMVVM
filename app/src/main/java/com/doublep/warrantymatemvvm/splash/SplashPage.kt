package com.doublep.warrantymatemvvm.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import com.doublep.warrantymatemvvm.base.BaseActivity
import com.doublep.warrantymatemvvm.base.BaseFragment
import com.doublep.warrantymatemvvm.databinding.SplashPageBinding
import com.doublep.warrantymatemvvm.login.view.LoginSignupFragment


class SplashPage : BaseFragment<SplashPageBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> SplashPageBinding
        get() = SplashPageBinding :: inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(2000) {
            (activity as BaseActivity).setFragToBaseView(LoginSignupFragment(),false)
        }
    }

}
package com.doublep.warrantymatemvvm.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.doublep.warrantymatemvvm.R
import com.doublep.warrantymatemvvm.base.BaseActivity
import com.doublep.warrantymatemvvm.base.BaseFragment
import com.doublep.warrantymatemvvm.databinding.LoginAndSigninPageBinding
import com.doublep.warrantymatemvvm.db.data.LoginEntity
import com.doublep.warrantymatemvvm.db.setup.MainDB
import com.doublep.warrantymatemvvm.home.view.HomeBase
import com.doublep.warrantymatemvvm.login.viewmodel.LoginSignupViewModel
import com.doublep.warrantymatemvvm.login.vmfactory.LoginSignupVMFactory
import com.doublep.warrantymatemvvm.repo.WMRepository


class LoginSignupFragment : BaseFragment<LoginAndSigninPageBinding>() {

    lateinit var loginVM: LoginSignupViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> LoginAndSigninPageBinding
        get() = LoginAndSigninPageBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var loginDao =
            MainDB.getDatabase((activity as BaseActivity).applicationContext).getCommonDao()
        var repo = WMRepository(loginDao)
        loginVM =
            ViewModelProvider(this, LoginSignupVMFactory(repo))[LoginSignupViewModel::class.java]
        setClicks()
        setLoginViews(true)
        loginVM.loginResult.observe(viewLifecycleOwner) { response ->
            if (response.contains("Successful",ignoreCase = true))
                (activity as BaseActivity).setFragToBaseView(HomeBase(),false)
            Toast.makeText(activity, response, Toast.LENGTH_LONG).show()

        }
    }

    private fun setLoginViews(isLoginScreenVisible: Boolean) {
        if (isLoginScreenVisible){
            val params = binding.bckWhiteView.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = binding.guideLnTagLine.id
            binding.bckWhiteView.layoutParams = params
            binding.bckWhiteView.background = ContextCompat.getDrawable(requireContext(),R.drawable.curve_edge_bck)

            binding.bckWhiteView.translationY = binding.bckWhiteView.height.toFloat()

            // Animate upward
            binding.bckWhiteView.animate()
                .translationY(0f)
                .setDuration(1000) // adjust duration as needed
                .setInterpolator(DecelerateInterpolator()) // smooth ease-out
                .start()

            binding.lblWelcome.text = resources.getString(R.string.lbl_welcome_back)
            binding.lblLoginAcc.text = resources.getString(R.string.lbl_login_acc)
            binding.lblNoAcc.text = resources.getString(R.string.lbl_no_acc)
            binding.btnLogin.text = resources.getString(R.string.btn_login)
            binding.btnSignup.visibility = View.VISIBLE
            binding.edtEmail.visibility = View.GONE
        }
        else{
            val params = binding.bckWhiteView.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = ConstraintLayout.LayoutParams.MATCH_PARENT
            binding.bckWhiteView.layoutParams = params
            binding.bckWhiteView.setBackgroundColor(resources.getColor(R.color.white))

            binding.bckWhiteView.translationY = binding.bckWhiteView.height.toFloat()

            // Animate upward
            binding.bckWhiteView.animate()
                .translationY(0f)
                .setDuration(1000) // adjust duration as needed
                .setInterpolator(DecelerateInterpolator()) // smooth ease-out
                .start()

            binding.lblWelcome.text = resources.getString(R.string.lbl_register)
            binding.lblLoginAcc.text = resources.getString(R.string.lbl_create_acc)
            binding.lblNoAcc.text = resources.getString(R.string.lbl_already_acc)
            binding.btnLogin.text = resources.getString(R.string.btn_sign_in)
            binding.btnSignup.visibility = View.GONE
            binding.edtEmail.visibility = View.VISIBLE
        }

    }

    private fun setClicks() {

        binding.btnLogin.setOnClickListener {
            val loginId = binding.edtLoginId.text.toString()
            val password = binding.edtPassword.text.toString()
            if (binding.btnLogin.text == "Login") {
                loginVM.clickLogin(loginId, password)
            }else{
                val entity = LoginEntity(0, loginId, password)
                loginVM.insertLoginDetails(entity)
                setLoginViews(true)
                binding.btnLogin.performClick()
                Toast.makeText(activity, "Data Added Successfully", Toast.LENGTH_LONG).show()
            }
        }

        binding.lblNoAcc.setOnClickListener {
            setLoginViews(true)
        }

        binding.btnSignup.setOnClickListener {
            setLoginViews(false)
        }
    }
}
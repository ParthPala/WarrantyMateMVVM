package com.doublep.warrantymatemvvm.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.doublep.warrantymatemvvm.R
import com.doublep.warrantymatemvvm.splash.SplashPage

class BaseActivity : AppCompatActivity() {
    private lateinit var ft: FragmentManager
    private var doubleBackToEXitPressOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        setFragToBaseView(SplashPage(), false)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.i("LauncherX:BaseAct", "=== in count of backstack == " + ft.backStackEntryCount)
                if (ft.backStackEntryCount < 1) {
                    if (doubleBackToEXitPressOnce) {
                        finish()
                        return
                    }

                    doubleBackToEXitPressOnce = true
                    Toast.makeText(this@BaseActivity, "Please press again!!", Toast.LENGTH_LONG)
                        .show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        doubleBackToEXitPressOnce = false
                    }, 2000)

                } else
                    pressBackToFragment()
            }

        })
    }

    fun setFragToBaseView(fragment: Fragment, addToBackStack: Boolean) {
        ft = supportFragmentManager
        if (addToBackStack) {
            ft.beginTransaction()
                .replace(R.id.baseContainer, fragment)
                .addToBackStack(fragment.toString())
                .commit()
        } else {
            ft.beginTransaction()
                .replace(R.id.baseContainer, fragment)
                .commit()
        }
    }

    fun pressBackToFragment() {
        ft.popBackStack()
    }


}
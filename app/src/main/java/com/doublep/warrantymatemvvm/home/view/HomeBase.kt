package com.doublep.warrantymatemvvm.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.doublep.warrantymatemvvm.R
import com.doublep.warrantymatemvvm.addWarranty.view.AddNewWarrantyFragment
import com.doublep.warrantymatemvvm.allWarranty.view.AllWarranties
import com.doublep.warrantymatemvvm.base.BaseActivity
import com.doublep.warrantymatemvvm.base.BaseFragment
import com.doublep.warrantymatemvvm.databinding.HomeBaseBinding
import com.doublep.warrantymatemvvm.db.data.HomeEntity
import com.doublep.warrantymatemvvm.db.setup.MainDB
import com.doublep.warrantymatemvvm.expirySoon.view.ExpirySoon
import com.doublep.warrantymatemvvm.home.viewmodel.HomeViewModel
import com.doublep.warrantymatemvvm.home.viewmodelfactory.HomeViewModelFactory
import com.doublep.warrantymatemvvm.repo.WMRepository


class HomeBase : BaseFragment<HomeBaseBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> HomeBaseBinding
        get() = HomeBaseBinding::inflate

    lateinit var homeVm: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var loginDao =
            MainDB.getDatabase((activity as BaseActivity).applicationContext).getCommonDao()
        var repo = WMRepository(loginDao)
        homeVm = ViewModelProvider(this, HomeViewModelFactory(repo))[HomeViewModel::class]
        setClicks()
        setAdapter()
        setData()
    }

    private fun setData() {
        homeVm.getWarrantyDetailsCount()
        val expList = resources.getStringArray(R.array.ExpiryTenure)
        homeVm.getExpiryCount(expList.take(4))

        homeVm.result.observe(viewLifecycleOwner) { count ->
            binding.tvWarrantyCount.text = count.toString()
        }

        homeVm.resultExp.observe(viewLifecycleOwner) { count ->
            binding.tvExpireCount.text = count.toString()
        }

    }

    private fun setClicks() {
        binding.btnAddWarranty.setOnClickListener {
            Log.i("LauncherX:home", "== click all warranty == ")
            (activity as BaseActivity).setFragToBaseView(AddNewWarrantyFragment(), true)
        }

        binding.lblAllWarranty.setOnClickListener {
            Log.i("LauncherX:home", "== click all warranty == ")
            (activity as BaseActivity).setFragToBaseView(AllWarranties(), true)
        }

        binding.lblExpiring.setOnClickListener {
            Log.i("LauncherX:home", "== click expire soon == ")
            (activity as BaseActivity).setFragToBaseView(ExpirySoon(), true)
        }

    }

    private fun setAdapter() {
        binding.recyList.addItemDecoration(
            DividerItemDecoration(
                binding.recyList.context,
                DividerItemDecoration.VERTICAL
            )
        )
        homeVm.getWarrantyDetails().observe(viewLifecycleOwner) { it ->
            binding.recyList.adapter = Adapter(it)
        }

    }
}
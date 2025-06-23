package com.doublep.warrantymatemvvm.expirySoon.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.doublep.warrantymatemvvm.R
import com.doublep.warrantymatemvvm.base.BaseActivity
import com.doublep.warrantymatemvvm.base.BaseFragment
import com.doublep.warrantymatemvvm.databinding.ExpirySoonBinding
import com.doublep.warrantymatemvvm.db.setup.MainDB
import com.doublep.warrantymatemvvm.expirySoon.viewmodel.ExpiryViewModel
import com.doublep.warrantymatemvvm.expirySoon.viewmodelfactory.ExpiryVMFactory
import com.doublep.warrantymatemvvm.home.view.Adapter
import com.doublep.warrantymatemvvm.repo.WMRepository

class ExpirySoon : BaseFragment<ExpirySoonBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ExpirySoonBinding
        get() = ExpirySoonBinding ::inflate

    lateinit var expiryVM : ExpiryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = MainDB.getDatabase(activity as BaseActivity).getCommonDao()
        val repo = WMRepository(dao)
        expiryVM = ViewModelProvider(this, ExpiryVMFactory(repo))[ExpiryViewModel::class]

        setAdapter()

    }

    private fun setAdapter() {
        binding.recyExpiryList.addItemDecoration(
            DividerItemDecoration(
                binding.recyExpiryList.context,
                DividerItemDecoration.VERTICAL
            )
        )

        val expirySoonList = resources.getStringArray(R.array.ExpiryTenure).take(4)

        expiryVM.getWarrantyDetails(expirySoonList).observe(viewLifecycleOwner) {
                it ->
            binding.recyExpiryList.adapter = Adapter(it)
        }
    }
}
package com.doublep.warrantymatemvvm.allWarranty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.doublep.warrantymatemvvm.allWarranty.viewmodel.AllWarrantyVM
import com.doublep.warrantymatemvvm.allWarranty.viewmodelfactory.AllWarrantyVMFactory
import com.doublep.warrantymatemvvm.base.BaseActivity
import com.doublep.warrantymatemvvm.base.BaseFragment
import com.doublep.warrantymatemvvm.databinding.AllWarrantiesBinding
import com.doublep.warrantymatemvvm.db.setup.MainDB
import com.doublep.warrantymatemvvm.home.view.Adapter
import com.doublep.warrantymatemvvm.repo.WMRepository

class AllWarranties : BaseFragment<AllWarrantiesBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AllWarrantiesBinding
        get() = AllWarrantiesBinding::inflate

    lateinit var allWarrantiesVM : AllWarrantyVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = MainDB.getDatabase(activity as BaseActivity).getCommonDao()
        val repo = WMRepository(dao)
        allWarrantiesVM = ViewModelProvider(this, AllWarrantyVMFactory(repo))[AllWarrantyVM::class]
        setAdapter()
    }

    private fun setAdapter() {
        binding.recyAllWarranty.addItemDecoration(
            DividerItemDecoration(
                binding.recyAllWarranty.context,
                DividerItemDecoration.VERTICAL
            )
        )

        allWarrantiesVM.getWarrantyDetails().observe(viewLifecycleOwner) {
                it ->
            binding.recyAllWarranty.adapter = Adapter(it)
        }
    }
}
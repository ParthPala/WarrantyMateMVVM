package com.doublep.warrantymatemvvm.addWarranty.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.doublep.warrantymatemvvm.R
import com.doublep.warrantymatemvvm.addWarranty.viewmodel.AddWarrantyViewModel
import com.doublep.warrantymatemvvm.addWarranty.viewmodelfactory.AddWarrantyViewModelFactory
import com.doublep.warrantymatemvvm.base.BaseActivity
import com.doublep.warrantymatemvvm.base.BaseFragment
import com.doublep.warrantymatemvvm.databinding.CreateWarrantyBinding
import com.doublep.warrantymatemvvm.db.setup.MainDB
import com.doublep.warrantymatemvvm.home.view.HomeBase

import com.doublep.warrantymatemvvm.repo.WMRepository


class AddNewWarrantyFragment : BaseFragment<CreateWarrantyBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> CreateWarrantyBinding
        get() = CreateWarrantyBinding::inflate
    lateinit var addViewModel: AddWarrantyViewModel
    lateinit var acContext: BaseActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        acContext = activity as BaseActivity
        val dao = MainDB.getDatabase(acContext.applicationContext).getCommonDao()
        val repo = WMRepository(dao)
        addViewModel =
            ViewModelProvider(this, AddWarrantyViewModelFactory(repo))[AddWarrantyViewModel::class]
        addViewModel.result.observe(viewLifecycleOwner) { response ->
            Toast.makeText(acContext, response, Toast.LENGTH_LONG).show()
            if (response.contains("successful"))
                acContext.pressBackToFragment()
        }
        setAdapterToSpinner()
        setClicks()
    }

    private fun setAdapterToSpinner() {
        val typeAdapter = ArrayAdapter(
            acContext,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.itemTypeArray)
        )
        val expiryAdapter = ArrayAdapter(
            acContext,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.ExpiryTenure)
        )

        binding.etProductType.adapter = typeAdapter
        binding.etProductCalendar.adapter = expiryAdapter
    }

    private fun setClicks() {
        binding.btnSave.setOnClickListener {
            val name = binding.etProductName.text.toString()
            val type = binding.etProductType.selectedItem.toString()
            val expiry = binding.etProductCalendar.selectedItem.toString()
            addViewModel.addDetails(name, type, expiry, R.drawable.ic_invoice)
        }
    }
}
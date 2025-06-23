package com.doublep.warrantymatemvvm.repo

import androidx.lifecycle.LiveData
import com.doublep.warrantymatemvvm.db.data.LoginEntity
import com.doublep.warrantymatemvvm.db.dao.CommonDao
import com.doublep.warrantymatemvvm.db.data.HomeEntity

class WMRepository(private val commonDao: CommonDao) {

    fun getLoginDetails(loginId: String, password: String) : LoginEntity?{
       return commonDao.getLoginDetails(loginId,password)
    }

    suspend fun insertLoginDetails(loginEntity: LoginEntity){
        commonDao.insertLoginDetails(loginEntity)
    }

    fun getWarrantyDetails(): LiveData<List<HomeEntity>>{
        return commonDao.getWarrantyDetails()
    }

    suspend fun insertWarrantyDetails(homeEntity: HomeEntity){
        commonDao.insertWarrantyDetails(homeEntity)
    }

    fun getWarrantyDetailsCount() : LiveData<Int>{
        return commonDao.getDataCount()
    }

}
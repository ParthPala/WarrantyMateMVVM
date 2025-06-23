package com.doublep.warrantymatemvvm.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.doublep.warrantymatemvvm.db.data.HomeEntity
import com.doublep.warrantymatemvvm.db.data.LoginEntity

@Dao
interface CommonDao {

    @Query("SELECT * FROM login WHERE mLoginId =:loginId AND mPassword =:password LIMIT 1")
    fun getLoginDetails(loginId : String, password : String) : LoginEntity?

    @Insert
    suspend fun insertLoginDetails(loginEntity: LoginEntity)

    @Insert
    suspend fun insertWarrantyDetails(homeEntity: HomeEntity)

    @Query("SELECT * FROM warrantydetails")
    fun getWarrantyDetails(): LiveData<List<HomeEntity>>

    @Query("SELECT COUNT(itemId) FROM warrantydetails")
    fun getDataCount(): LiveData<Int>

}
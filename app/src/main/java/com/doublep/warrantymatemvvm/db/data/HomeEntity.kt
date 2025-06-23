package com.doublep.warrantymatemvvm.db.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WarrantyDetails")
data class HomeEntity(
    @PrimaryKey(autoGenerate = true)
    val itemId : Int,
    val itemName : String,
    val itemExpiryDate : String,
    val itemType : String,
    val itemPic : Int
)

package com.doublep.warrantymatemvvm.db.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
data class LoginEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val mLoginId: String,
    val mPassword: String
)

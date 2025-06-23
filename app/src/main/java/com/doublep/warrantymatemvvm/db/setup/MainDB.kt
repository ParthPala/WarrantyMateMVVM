package com.doublep.warrantymatemvvm.db.setup

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.doublep.warrantymatemvvm.db.dao.CommonDao
import com.doublep.warrantymatemvvm.db.data.HomeEntity
import com.doublep.warrantymatemvvm.db.data.LoginEntity

@Database(entities = [LoginEntity::class, HomeEntity::class], version = 2)
abstract class MainDB : RoomDatabase() {

    abstract fun getCommonDao(): CommonDao
    companion object {
        private var _INSTANCE: MainDB? = null
        fun getDatabase(context: Context): MainDB {
            if (_INSTANCE == null) {
                synchronized(this) {
                    _INSTANCE =
                        Room.databaseBuilder(
                            context,
                            MainDB::class.java,
                            "warrantyMateDB")
                            .build()
                }
            }
            return _INSTANCE!!
        }
    }
}
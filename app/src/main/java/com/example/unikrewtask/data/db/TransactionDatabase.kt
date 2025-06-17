package com.example.unikrewtask.data.db

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unikrewtask.data.model.Transaction


@Database(entities = [Transaction::class],version = 1)
abstract class   TransactionDatabase:RoomDatabase() {

    abstract val subscriberDAO : TransactionDao

    companion object{
        @Volatile
        private var INSTANCE : TransactionDatabase? = null
        @SuppressLint("SuspiciousIndentation")
        fun getInstance(context: Context):TransactionDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TransactionDatabase::class.java,
                        "subscriber_data_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
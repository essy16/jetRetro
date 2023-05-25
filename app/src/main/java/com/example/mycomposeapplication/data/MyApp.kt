package com.example.mycomposeapplication.data

import android.app.Application
import androidx.room.Room
import com.example.mycomposeapplication.MyApplication
import com.example.mycomposeapplication.network.ApiService
import com.example.mycomposeapplication.network.DataObject

class MyApp : MyApplication() {
    override fun onCreate() {
        super.onCreate()
        // Initialize ApiService, UserDatabase, and other dependencies here
        val apiService = DataObject.dataInstance
        val userDatabase = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "user-database"
        ).build()


    }
}

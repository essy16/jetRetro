package com.example.mycomposeapplication

import android.app.Application
import android.content.Context
import com.example.mycomposeapplication.data.UserDatabase
import com.example.mycomposeapplication.data.UserRepository
import com.example.mycomposeapplication.network.ApiService
import com.example.mycomposeapplication.network.DataObject

open class MyApplication : Application() {
    private lateinit var apiService: ApiService
    private lateinit var userDatabase: UserDatabase

    val repository: UserRepository by lazy {
        UserRepository(apiService, userDatabase, applicationContext)
    }

    companion object {
        lateinit var appInstance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        // Initialize ApiService, UserDatabase, and other dependencies here
        apiService = DataObject.dataInstance
        userDatabase = UserDatabase.getDatabase(applicationContext)
    }
}

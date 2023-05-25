package com.example.mycomposeapplication.data

import android.content.Context
import android.net.ConnectivityManager

import com.example.mycomposeapplication.network.ApiService

class UserRepository(
    private val apiService: ApiService,
    private val userDatabase: UserDatabase,
    private val context: Context
) {
    suspend fun getUsers(): Result<List<User>> {
        if (isNetworkAvailable()) {
            try {
                val response = apiService.getUsers()
                if (response.isSuccessful) {
                    val users = response.body()
                    users?.let {
                        userDatabase.userDao().addUser(it)
                        return Result.success(it)
                    }
                }
            } catch (e: Exception) {
                // Handle exception if needed
                return Result.failure(e)
            }
        }
        val cacheUsers = userDatabase.userDao().getUser()
        return Result.success(cacheUsers)
    }

    suspend fun getUserById(userId: Int): User {
        return userDatabase.userDao().getUserById(userId)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

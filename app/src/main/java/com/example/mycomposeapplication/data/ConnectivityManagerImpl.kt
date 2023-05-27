package com.example.mycomposeapplication.data

import android.content.Context

class ConnectivityManagerImpl(
    private val context: Context
) : ConnectivityManager {
    override fun getNetworkStatus(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
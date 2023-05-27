package com.example.mycomposeapplication.data

import android.app.Application
import androidx.room.Room
import com.example.mycomposeapplication.network.ApiService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application()

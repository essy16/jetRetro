package com.example.mycomposeapplication.network

import androidx.lifecycle.LiveData
import com.example.mycomposeapplication.data.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://dummyjson.com/"

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}

    object DataObject {     // we will implement our interface in this object so that we can call methods of our interface
        val dataInstance: ApiService // reference of interface

        init {
            val retrofit = Retrofit.Builder()   //retrofit object
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //add convertor
                .build()
            // tell retrofit object to give implementation of out interface
            dataInstance = retrofit.create(ApiService::class.java)
        }
    }
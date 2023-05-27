package com.example.mycomposeapplication.network

import com.example.mycomposeapplication.data.UserDto
import com.example.mycomposeapplication.data.UserEntity
import com.example.mycomposeapplication.data.UsersDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

const val BASE_URL = "https://dummyjson.com/"

interface ApiService {
    @GET("users")
    suspend fun getUsers(): UsersDto
}


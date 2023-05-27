package com.example.mycomposeapplication.data

import com.example.mycomposeapplication.utils.Resource
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun getUsers() : Flow<Resource<List<UserDto>>>

    suspend fun getUserById(userId : Int) : UserEntity
}
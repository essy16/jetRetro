package com.example.mycomposeapplication.data

import android.util.Log
import com.example.mycomposeapplication.network.ApiService
import com.example.mycomposeapplication.utils.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl  @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao ,
    private val connectivityManager: ConnectivityManager
) : UserRepository {

    companion object {
        const val TAG = "UserRepositoryImpl"
    }
    override fun getUsers(): Flow<Resource<List<UserDto>>> = flow {
        val isNetworkConnected = connectivityManager.getNetworkStatus()
        if (isNetworkConnected) {
            try {
                val response = apiService.getUsers()

                userDao.addUser(
                    response.users
                        .map {
                            it.mapToEntity()
                        }
                )

                emit(Resource.Success(
                    response.users
                ))
               /* if (response.isSuccessful) {
                    val usersJson = response.body().toString()
                    *//*val listType  = object : TypeToken<List<UserDto>>() {}.type
                    val usersDtoList : List<UserDto> = Gson().fromJson(
                        usersJson ,
                        listType
                    )*//*
                    userDao.addUser(
                        usersDtoList.map {
                            it.mapToEntity()
                        }
                    )

                    emit(Resource.Success(
                        usersDtoList
                    ))

                }else {
                    val errorMessage = "${response.code()} : ${response.errorBody()}"
                    emit(Resource.Error(message = errorMessage))
                    Log.d(TAG, "getUsers: errorMessage")
                }*/
            } catch (e: Exception) {
                // Handle exception if needed
                Log.d(TAG, "getUsers: ${e.stackTrace}")
                emit(Resource.Error(message = e.localizedMessage))
            }
        }else {
            val cacheUsers = userDao.getUser()
            emit(Resource.Success(cacheUsers.map { it.mapToDto() }))
        }
    }

    override suspend fun getUserById(userId: Int): UserEntity {
        return userDao.getUserById(userId)
    }

    private fun UserDto.mapToEntity() : UserEntity {
        return UserEntity(
            id = id ,
            firstName = firstName ,
            lastName = lastName ,
            age = age ,
            email = email
        )
    }

    private fun UserEntity.mapToDto() : UserDto {
        return UserDto(
            id = id ,
            firstName = firstName ,
            lastName = lastName ,
            age = age ,
            email = email
        )
    }

}

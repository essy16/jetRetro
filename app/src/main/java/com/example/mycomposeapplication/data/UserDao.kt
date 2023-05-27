package com.example.mycomposeapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Contains methods used for accessing our database
@Dao
interface UserDao {
    //Create queries which we're going to execute inside our DB
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    suspend fun getUser() : List<UserEntity>    //RETURNS LIST OF USERS WRAPPED IN LIVE DATA

    @Query("SELECT * FROM user_table WHERE id = :userId")
    suspend fun getUserById(userId : Int): UserEntity

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun addUser(userEntity: List<UserEntity>)         //ADD USER



}
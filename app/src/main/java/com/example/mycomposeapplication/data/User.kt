package com.example.mycomposeapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Represents entity(Table) in our database

@Entity(tableName = "user_table")
data class User(
    // Column headings of our table
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val email: String
)

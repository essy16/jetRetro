package com.example.mycomposeapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

// This class will represent database

// UserDatabase class will be a singleton class i.e only 1 instance

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    // everything in companion object will be visible to other classes
    companion object{
        @Volatile
        // READS AND WRITES TO THIS INSTANCE VARIABLE IS VISIBLE TO OTHER THREADS BECAUSE
        // ANNOTATED AS VOLATILE
        private var INSTANCE: UserDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context : Context) : UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            // synchronised block create new instance if null
            synchronized(this){
                // everything in this block will be protected from concurrent executions by multiple
                // threads.
                val instance = Room.databaseBuilder(
                    // creating instance for our database
                    context.applicationContext, // passing context
                    UserDatabase::class.java,     // passing database
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
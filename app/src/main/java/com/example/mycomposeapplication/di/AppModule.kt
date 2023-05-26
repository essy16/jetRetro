package com.example.mycomposeapplication.di

import android.content.Context
import com.example.mycomposeapplication.data.UserDatabase
import com.example.mycomposeapplication.data.UserRepository
import com.example.mycomposeapplication.network.ApiService
import com.example.mycomposeapplication.network.DataObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideApiService(): ApiService {
        return DataObject.dataInstance
    }

    @Provides
    fun provideUserDatabase(context: Context): UserDatabase {
        return UserDatabase.getDatabase(context)
    }

    @Provides
    fun provideUserRepository(
        apiService: ApiService,
        userDatabase: UserDatabase,
        context: Context
    ): UserRepository {
        return UserRepository(apiService, userDatabase, context)
    }
}


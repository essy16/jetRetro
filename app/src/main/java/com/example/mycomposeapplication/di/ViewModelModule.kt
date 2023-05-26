package com.example.mycomposeapplication.di

import com.example.mycomposeapplication.data.UserRepository
import com.example.mycomposeapplication.data.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

// ViewModelModule.kt
@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {
    @Provides
    fun provideUserViewModel(userRepository: UserRepository): UserViewModel {
        return UserViewModel(userRepository)
    }
}

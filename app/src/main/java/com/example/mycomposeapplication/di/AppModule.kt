package com.example.mycomposeapplication.di

import android.content.Context
import androidx.room.Room
import com.example.mycomposeapplication.data.ConnectivityManager
import com.example.mycomposeapplication.data.ConnectivityManagerImpl
import com.example.mycomposeapplication.data.UserDao
import com.example.mycomposeapplication.data.UserDatabase
import com.example.mycomposeapplication.data.UserRepositoryImpl
import com.example.mycomposeapplication.network.ApiService
import com.example.mycomposeapplication.network.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()   //retrofit object
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //add convertor
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(
        @ApplicationContext context : Context
    ): UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext ,
            UserDatabase::class.java ,
            "user_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(
        db : UserDatabase
    ) : UserDao {
        return db.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        apiService: ApiService,
        userDao: UserDao,
        connectivityManager: ConnectivityManager
    ): UserRepositoryImpl {
        return UserRepositoryImpl(apiService , userDao , connectivityManager)
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext context : Context
    ) : ConnectivityManager {
        return ConnectivityManagerImpl(context)
    }

}


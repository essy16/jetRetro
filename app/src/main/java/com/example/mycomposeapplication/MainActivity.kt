package com.example.mycomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.mycomposeapplication.data.*
import com.example.mycomposeapplication.`interface`.LightTheme
import com.example.mycomposeapplication.navigation.Navigation
import com.example.mycomposeapplication.network.DataObject
import dagger.hilt.android.AndroidEntryPoint
import kotlin.Result

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    private lateinit var userRepository: UserRepository
    val userViewModel : UserViewModel by viewModels { UserViewModel.provideViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = DataObject.dataInstance
        val userDatabase = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "user-database"
        ).build()
        val applicationContext = application.applicationContext
        userRepository = UserRepository(apiService, userDatabase, applicationContext)

        setContent {
            LightTheme {
                MyApp(userRepository)
            }
        }
    }
}


@Composable
fun MyApp(userRepository: UserRepository) {
    ProvideViewModel(userRepository = userRepository) {
        val userViewModel = LocalUserViewModel.current
        val users: Result<List<User>>? = userViewModel.users.value
        Navigation()
    }
}


@Composable
fun ProvideViewModel(userRepository: UserRepository, content: @Composable () -> Unit) {
    val viewModel: UserViewModel = viewModel(factory = UserViewModel.provideViewModelFactory())
    CompositionLocalProvider(LocalUserViewModel provides viewModel) {
        content()
    }
}


val LocalUserViewModel = staticCompositionLocalOf<UserViewModel> {
    error("No UserViewModel Provided")
}

package com.example.mycomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.example.mycomposeapplication.data.*
import com.example.mycomposeapplication.`interface`.LightTheme
import com.example.mycomposeapplication.navigation.Navigation
import com.example.mycomposeapplication.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlin.Result

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var mUserRepositoryImpl: UserRepositoryImpl
    private val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val usersListState = userViewModel.users.collectAsState()
            LightTheme {
                MyApp(
                    usersListState = usersListState
                )
            }
        }
    }
}


@Composable
fun MyApp(
    usersListState : State<Resource<List<UserDto>>>
) {
    Navigation(
      usersListState = usersListState
    )
}


package com.example.mycomposeapplication.`interface`

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycomposeapplication.Result
import com.example.mycomposeapplication.data.User
import com.example.mycomposeapplication.data.UserViewModel


@Composable
fun UserDetail(userId: Int) {
    val userViewModel: UserViewModel = viewModel()
    var userState by remember(userId) {
        mutableStateOf<Result<User>>(Result.Loading)
    }

    LaunchedEffect(userId) {
        try {
            val user = userViewModel.getUserById(userId)
            userState = Result.Success(user)
        } catch (e: Exception) {
            userState = Result.Failure(e)
        }
    }

    when (val result = userState) {
        is Result.Success -> {
            val user = result.value

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = user.firstName, style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = user.lastName, style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = user.email, style = MaterialTheme.typography.body1)
                }
            }
        }
        is Result.Failure -> {
            val error = result.throwable
            // Handle the failure state with the error
        }
        is Result.Loading -> {
            // Handle the loading state
        }
    }
}


package com.example.mycomposeapplication.`interface`

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycomposeapplication.data.User
import com.example.mycomposeapplication.data.UserViewModel
import androidx.compose.material.ListItem
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
@Composable
fun UserListScreen(navigateToUserDetail: (User) -> Unit) {
    val userViewModel: UserViewModel = viewModel()
    val usersResult: Result<List<User>>? = userViewModel.users.observeAsState(initial = null).value
    val users: List<User> = usersResult?.getOrNull() ?: emptyList()

    Column {
        LazyColumn {
            itemsIndexed(users) { index, user ->
                UserListItem(user = user) {
                    navigateToUserDetail(user)
                }
                Divider()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserListItem(user: User, onCliCk: () -> Unit){
    ListItem(
        text ={ Text(text = user.firstName)},
        secondaryText = { Text(text = user.lastName)},
        modifier = Modifier.clickable {onCliCk()}
    )
}


package com.example.mycomposeapplication.`interface`

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycomposeapplication.data.UserEntity
import com.example.mycomposeapplication.data.UserViewModel
import androidx.compose.material.ListItem
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.example.mycomposeapplication.Result
import com.example.mycomposeapplication.data.UserDto
import com.example.mycomposeapplication.utils.Resource

@Composable
fun UserListScreen(
    navigateToUserDetail: (UserDto) -> Unit ,
    usersListState : State<Resource<List<UserDto>>>
) {

    val context = LocalContext.current

    Column {

        when(val usersResult = usersListState.value){
            is  Resource.Loading -> {
                Toast.makeText(context , "Fetching users list", Toast.LENGTH_SHORT).show()
            }

            is Resource.Success -> {
                usersResult.data?.let { users ->
                    LazyColumn {
                        itemsIndexed(users) { index, user ->
                            UserListItem(user = user) {
                                navigateToUserDetail(user)
                            }
                            Divider()
                        }
                    }
                } ?: Text(text = "Could not fetch user data from network")


            }

            is Resource.Error -> {
                Text(text = "Could not fetch users data: ${usersResult.message}")
            }
        }


    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserListItem(user: UserDto, onCliCk: () -> Unit){
    ListItem(
        text ={ Text(text = user.firstName)},
        secondaryText = { Text(text = user.lastName)},
        modifier = Modifier.clickable {onCliCk()}
    )
}


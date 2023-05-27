package com.example.mycomposeapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycomposeapplication.data.UserDto
import com.example.mycomposeapplication.`interface`.UserDetail
import com.example.mycomposeapplication.`interface`.UserListScreen
import com.example.mycomposeapplication.data.UserEntity
import com.example.mycomposeapplication.utils.Resource

sealed class Screen(val route: String) {
    object UserList : Screen("userList")
    data class UserDetail(val userId: Int) : Screen("userDetail/{userId}")
}

@Composable
fun Navigation(
    usersListState: State<Resource<List<UserDto>>>
) {
    val navController = rememberNavController()
    val actions = remember(navController) {
        NavigationActions(navController)
    }

    NavHost(navController, startDestination = Screen.UserList.route) {
        composable(
            route = Screen.UserList.route
        ) {
            UserListScreen(
                navigateToUserDetail = {
                    //actions.navigateToUserDetail
                    navController.navigate(
                       "userDetail/${it.id}"
                    )
                },
                usersListState = usersListState
            )
        }
        composable(
            route = "userDetail/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")
            userId?.let {
                UserDetail(userId = it)
            }
        }
    }
}

class NavigationActions(navController: NavHostController) {
    val navigateToUserDetail: (UserDto) -> Unit = { user ->
        //navController.navigate(Screen.UserDetail.route.replace("{userId}",user.id.toString().toUri()))
        //   navController.navigate("userDetail/${user.id}")
        navController.navigate(Screen.UserDetail(user.id).route)
    }
}
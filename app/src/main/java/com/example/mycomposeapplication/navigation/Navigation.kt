package com.example.mycomposeapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycomposeapplication.`interface`.UserDetail
import com.example.mycomposeapplication.`interface`.UserListScreen
import com.example.mycomposeapplication.data.User

sealed class Screen(val route: String){
    object UserList : Screen("userList")
    data class UserDetail( val userId: Int) : Screen("userDetail/{userId}")
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val actions = remember(navController){
        NavigationActions(navController)}

    NavHost(navController, startDestination = Screen.UserList.route){
        composable(Screen.UserList.route){
            UserListScreen(actions.navigateToUserDetail)
        }
        composable(
            route = "userDetail/{userId}",
            arguments = listOf(navArgument("userId"){type = NavType.IntType})
        ){ backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")
            userId?.let{
                UserDetail(userId=it)
            }
        }
    }
}

class NavigationActions(navController: NavHostController){
    val navigateToUserDetail: (User) -> Unit ={user ->
        //navController.navigate(Screen.UserDetail.route.replace("{userId}",user.id.toString().toUri()))
         //   navController.navigate("userDetail/${user.id}")
        navController.navigate(Screen.UserDetail(user.id).route)
    }
}
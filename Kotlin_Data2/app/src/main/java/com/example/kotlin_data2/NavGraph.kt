package com.example.kotlin_data2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen (val route:String){
    object List:Screen("contactlist")
    object Detail:Screen("contactdetail")
    object Search:Screen("search")
}

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.List.route
    ) {
        composable(Screen.List.route) {
            ContactListScreen(navController)
        }
        composable(Screen.Search.route) {
            SearchScreen(navController)
        }
        composable(Screen.Detail.route+"?id={id}", arguments = listOf(navArgument("id"){nullable=true}))
        {
            var id=it.arguments?.getString("id")
            if(id!=null){
                ContactDetailScreen(navController = navController,id.toInt())
            }
            else{
                ContactDetailScreen(navController = navController)
            }
        }
    }
}
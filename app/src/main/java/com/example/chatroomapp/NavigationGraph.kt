package com.example.chatroomapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatroomapp.appScreens.ChatRoomListScreen
import com.example.chatroomapp.appScreens.LogInScreen
import com.example.chatroomapp.appScreens.SignUpScreen
import com.example.chatroomapp.viewModel.AuthViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = Screen.SignUpScreen.rout) {
        composable(Screen.SignUpScreen.rout) {
            SignUpScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = {
                navController.navigate(Screen.LogInScreen.rout)
            })
        }

        composable(Screen.LogInScreen.rout) {
            LogInScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = {
                navController.navigate(Screen.SignUpScreen.rout)
            }) {
                navController.navigate(Screen.ChatRoomScreen.rout)
            }
        }

        composable(Screen.ChatRoomScreen.rout) {
            ChatRoomListScreen()
        }
    }
}
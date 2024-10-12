package com.example.chatroomapp

sealed class Screen (val rout: String) {
    object LogInScreen: Screen("login")
    object SignUpScreen: Screen("signup")
    object ChatRoomScreen: Screen("chatroom")
    object ChatScreen: Screen("chatscreen")
}
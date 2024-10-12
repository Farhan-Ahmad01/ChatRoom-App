package com.example.chatroomapp.data

// The next class is a Result class that will hold the data state for each process.
sealed class Resultt<out T> {
    data class Success<out T>(val data: T) : Resultt<T>()
    data class Error(val exception: Exception) : Resultt<Nothing>()
}

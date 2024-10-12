package com.example.chatroomapp

import com.google.firebase.firestore.FirebaseFirestore

object Injection {
    //  For the Firestore we will need to create an object class which can make it accessible to other classes that will be dependent on it.
    private val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun instance(): FirebaseFirestore{
        return instance
    }
}
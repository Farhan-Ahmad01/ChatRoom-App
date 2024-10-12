package com.example.chatroomapp.repository

import com.example.chatroomapp.data.Resultt
import com.example.chatroomapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


// Starting with the UserRepository where the registration and sign-in will be created.
// We will provide the FirebaseAuth and the FirebaseFirestore to its constructor.
class UserRespository(private val auth: FirebaseAuth,
                      private val firestore: FirebaseFirestore
) {

    // Within the class we will create a Sing-up function to register a user with the email provider.
    suspend fun signUp(email: String, password: String, name: String) : Resultt<Boolean> =
        try {
            auth.createUserWithEmailAndPassword(email, password).await()

            // After a user is created we will then add them to firestore.
            // So within the signup function we will call saveUserToFirebase.
            val user = User(name, email)
            saveUserToFirestore(user)

            Resultt.Success(true)
        } catch (e: Exception) {
            Resultt.Error(e)
        }

    // Within the class we will create a function to save a user.
    private suspend fun saveUserToFirestore(user: User) {
        firestore.collection("users").document(user.email).set(user).await()
        // firestore saves data as documents within a collection.
        // For each user document we will save them in a collection called users providing their email as a key rather than letting firestore generate random characters.
        // With this email we can then manage each user better in the chatrooms.
    }

    // This will use the auth method signInWithEmailAndPassword to sign in users with their registered email and password.
    //  In AuthViewModel we can prepare the function for use and hold the result value using the authResult.
    suspend fun login(email: String, password: String): Resultt<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Resultt.Success(true)
        } catch (e: Exception) {
            Resultt.Error(e)
        }



} // main class end here

















package com.example.chatroomapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.Injection
import com.example.chatroomapp.repository.UserRespository
import com.google.firebase.auth.FirebaseAuth
import eu.tutorials.chatroomapp.data.Result
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    // We need to initialize the userRepository which is dependent on both the FirebaseAuth and FirestoreDatabase.
    // For the auth instance we can call it directly within the class without having to provide a global access to it
    // because this is the only class we would need it.

    //  For the Firestore we will need to create an object class which can make it accessible to other classes that will be dependent on it.
    private val userRespository: UserRespository
    init {
        // we can now create an instance of UserRepository within the init block providing its dependencies..
        userRespository = UserRespository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }

    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun signUp(email: String, password: String, name: String) {
        viewModelScope.launch {
            _authResult.value = userRespository.signUp(email, password, name)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRespository.login(email, password)
        }
    }

}






















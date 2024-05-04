package com.example.cookbook.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.cookbook.utils.LogInResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInScreenViewModel @Inject constructor(
) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var navController: NavHostController

    private val _logInResult = MutableLiveData<LogInResult>()
    val logInResult: LiveData<LogInResult> = _logInResult

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    fun logInUser(
        context: Context,
        email: String,
        password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _logInResult.value = LogInResult.Success
                } else {
                    _logInResult.value = LogInResult.Failure("Sign In Failed ")
                }
            }
    }
}

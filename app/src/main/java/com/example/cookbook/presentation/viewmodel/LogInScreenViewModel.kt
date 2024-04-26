package com.example.cookbook.presentation.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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
                    Log.d("LogInScreenViewModel", "_logInResult : $_logInResult")
                } else {
                    _logInResult.value = LogInResult.Failure("Sign In Failed ")
                }
            }
    }
}

sealed class LogInResult {
    object Success: LogInResult()
    data class Failure(val errorMessage: String): LogInResult()
}
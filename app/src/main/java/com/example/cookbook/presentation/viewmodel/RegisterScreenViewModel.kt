package com.example.cookbook.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookbook.utils.RegistrationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(

): ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _registrationResult = MutableLiveData<RegistrationResult>()
    val registrationResult: LiveData<RegistrationResult>  = _registrationResult

    fun registerUser(
        context: Context,
        name: String,
        email: String,
        password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()
                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { displayNameUpdateTask ->
                            if (displayNameUpdateTask.isSuccessful) {
                                _registrationResult.value = RegistrationResult.Success
                            } else {
                                _registrationResult.value = RegistrationResult
                                    .Failure("Failed to update display name")
                            }
                        }
                } else {
                    _registrationResult.value = RegistrationResult
                        .Failure("Registration Failed")
                }
            }
    }
}
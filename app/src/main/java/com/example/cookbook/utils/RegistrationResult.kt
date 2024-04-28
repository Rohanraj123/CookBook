package com.example.cookbook.utils

sealed class RegistrationResult {
    object Success: RegistrationResult()
    data class Failure(val errorMessage: String): RegistrationResult()
}
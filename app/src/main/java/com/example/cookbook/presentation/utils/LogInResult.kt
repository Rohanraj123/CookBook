package com.example.cookbook.presentation.utils

sealed class LogInResult {
    object Success: LogInResult()
    data class Failure(val errorMessage: String): LogInResult()
}
package com.example.cookbook.presentation.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.example.cookbook.utils.LogInResult
import com.example.cookbook.utils.PreferenceManager
import com.example.cookbook.presentation.view.components.CustomButton
import com.example.cookbook.presentation.view.components.CustomTextField
import com.example.cookbook.presentation.viewmodel.LogInScreenViewModel
import com.example.cookbook.ui.theme.ButtonColor

@Composable
fun LogInScreen(
    logInScreenViewModel: LogInScreenViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordLengthError by remember { mutableStateOf("") }
    val preferenceManager = remember { PreferenceManager(context) }
    var loggedIn by remember { mutableStateOf( preferenceManager.getBoolean("loggedIn", false))}
    
    DisposableEffect(Unit) {
        val observer = Observer<LogInResult> { logInResult ->
            when(logInResult) {
                is LogInResult.Success -> {
                    loggedIn = true
                    preferenceManager.saveBoolean("loggedIn", true)
                    navController.navigate("HomeScreen/${email}")
                }
                is LogInResult.Failure -> {
                    loggedIn = false
                    preferenceManager.saveBoolean("loggedIn", false)
                    navController.navigate("logInScreen")
                }
            }
        }

        val liveData = logInScreenViewModel.logInResult
        liveData.observeForever(observer)

        onDispose { liveData.removeObserver(observer) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello,",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        Text(
            text = "Welcome Back! ",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(25.dp))

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeHolder = "Email",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(19.dp))

        CustomTextField(
            value = password,
            onValueChange = {
                password = it
                passwordLengthError = if (password.length < 6) "Password should atleast be of 6 characters" else ""
                            },
            placeHolder = "Password",
            keyboardType = KeyboardType.Password
        )

        if (passwordLengthError.isNotBlank()) {
            Text(
                text = passwordLengthError,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(19.dp))

        CustomButton(
            onClick = {
                try {
                    logInScreenViewModel.logInUser(context, email, password)
                } catch (e : Exception) {
                    Toast.makeText(context, "Wrong credentials", Toast.LENGTH_LONG).show()
                }
            },
            text = "Sign In"
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 15.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Don't have an account? ",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sign Up",
                fontWeight = FontWeight.Bold,
                color = ButtonColor,
                modifier = Modifier.clickable { navController.navigate("RegisterScreen/{name}") }
            )
        }
    }
}

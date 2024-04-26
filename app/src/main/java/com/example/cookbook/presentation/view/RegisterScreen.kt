package com.example.cookbook.presentation.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import com.example.cookbook.presentation.view.components.CustomButton
import com.example.cookbook.presentation.view.components.CustomTextField
import com.example.cookbook.presentation.viewmodel.RegisterScreenViewModel
import com.example.cookbook.presentation.viewmodel.RegistrationResult
import com.example.cookbook.ui.theme.ButtonColor

@Composable
fun RegisterScreen(
    registerScreenViewModel: RegisterScreenViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var passwordMatchError by remember { mutableStateOf("") }

    DisposableEffect(registerScreenViewModel.registrationResult) {
        val observer = Observer<RegistrationResult> { result ->
            when(result) {
                is RegistrationResult.Success -> {
                    navController.navigate("HomeScreen")
                }
                is RegistrationResult.Failure -> {
                    Toast.makeText(context, result.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        val liveData = registerScreenViewModel.registrationResult
        liveData.observeForever(observer)
        onDispose {
            liveData.removeObserver(
                observer
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create an account,",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        Text(
            text = "Let's help you set up your account ",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(25.dp))

        CustomTextField(
            value = name,
            onValueChange = { name = it },
            placeHolder = "Name"
        )

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeHolder = "Email",
            keyboardType = KeyboardType.Email
        )

        CustomTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = if (it.length < 6) "Password should be at least 6 characters" else ""
            },
            placeHolder = "Password",
            keyboardType = KeyboardType.Password
        )

        if (passwordError.isNotEmpty()) {
            Text(
                text = passwordError,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        CustomTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                passwordMatchError =
                    if (confirmPassword != password) "Passwords do not match" else ""
            },
            placeHolder = "Confirm Password",
            keyboardType = KeyboardType.Password
        )

        if (passwordMatchError.isNotBlank()) {
            Text(
                text = passwordMatchError,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(19.dp))

        CustomButton(
            onClick = {
                if (confirmPassword == password) {

                    registerScreenViewModel.registerUser(context, name, email, password)
                }
            },
            text = "Sign Up"
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
                text = "Already have an account? ",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sign In",
                fontWeight = FontWeight.Bold,
                color = ButtonColor,
                modifier = Modifier.clickable { navController.navigate("logInScreen") }
            )
        }
    }
}

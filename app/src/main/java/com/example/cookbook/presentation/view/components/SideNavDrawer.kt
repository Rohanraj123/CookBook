package com.example.cookbook.presentation.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cookbook.utils.PreferenceManager

@Composable
fun SideNavDrawer(
    isDrawerOpen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onDrawerStateChange: (Boolean) -> Unit,
) {
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    val drawerState =
        rememberDrawerState(
            initialValue =
            if (isDrawerOpen) DrawerValue.Open
            else DrawerValue.Closed)

    LaunchedEffect(isDrawerOpen) {
        if (isDrawerOpen) {
            drawerState.open()
        } else {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Log Out") },
                    selected = false,
                    onClick = {
                        preferenceManager.saveBoolean("loggedIn", false)
                        navController.navigate("logInScreen")
                    }
                )
            }
        }
    ) {
        onDrawerStateChange(drawerState.isOpen)
    }
}

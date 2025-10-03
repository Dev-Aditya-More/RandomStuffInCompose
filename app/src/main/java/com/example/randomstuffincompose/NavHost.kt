package com.example.randomstuffincompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

sealed class Logos(val route : String){

    object Facebook : Logos("facebook")

    object Instagram : Logos("instagram")

    object Kotlin : Logos("kotlin")

    object Messenger : Logos("messenger")

    object Photos : Logos("photos")

    object Weather : Logos("weather")
}

@Composable
fun AppNavHost(navController: NavHostController, startDestination : String) {

    Scaffold { innerPadding ->
        NavHost(
            navController,
            startDestination
        ) {

            composable(Logos.Facebook.route) {

                FacebookLogo(Modifier.padding(innerPadding), navController)
            }

            composable(Logos.Instagram.route) {

                InstagramLogo(Modifier.padding(innerPadding), navController)
            }

            composable(Logos.Kotlin.route) {

                KotlinLogo(Modifier.padding(innerPadding), navController)
            }

            composable(Logos.Messenger.route) {

                MessengerLogo(Modifier.padding(innerPadding), navController)
            }

            composable(Logos.Photos.route) {

                PhotosLogo(Modifier.padding(innerPadding), navController)
            }

            composable(Logos.Weather.route) {

                WeatherIcon(Modifier.padding(innerPadding), navController)
            }

        }
    }
}
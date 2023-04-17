package com.example.dictionary.presentation.screens

sealed class Screens(val route: String){
    object MainScreen: Screens("main_screen")
}

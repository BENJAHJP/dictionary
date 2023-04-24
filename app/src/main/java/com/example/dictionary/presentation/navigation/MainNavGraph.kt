package com.example.dictionary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dictionary.presentation.search.components.MainScreen
import com.example.dictionary.presentation.screens.Screens

@Composable
fun MainNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.MainScreen.route){
        composable(Screens.MainScreen.route){ MainScreen() }
    }
}
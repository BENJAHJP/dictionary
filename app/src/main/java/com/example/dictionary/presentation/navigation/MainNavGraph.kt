package com.example.dictionary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dictionary.presentation.main.MainScreenViewModel
import com.example.dictionary.presentation.main.components.MainScreen
import com.example.dictionary.presentation.screens.Screens

@Composable
fun MainNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.MainScreen.route){
        composable(Screens.MainScreen.route){
            val viewModel: MainScreenViewModel = hiltViewModel()
            MainScreen(viewModel = viewModel)
        }
    }
}
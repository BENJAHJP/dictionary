package com.example.dictionary.presentation.main

sealed class MainScreenEvents {
    data class OnSearchTextChanged(val searchQuery: String): MainScreenEvents()
}
package com.example.dictionary.presentation.main

import com.example.dictionary.domain.model.WordInfo

data class MainScreenState(
    val isLoading: Boolean = false,
    val words: List<WordInfo> = emptyList(),
    val message: String = ""
)

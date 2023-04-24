package com.example.dictionary.presentation.search

sealed class SearchScreenEvents {
    data class OnSearchTextChanged(val searchQuery: String): SearchScreenEvents()
}
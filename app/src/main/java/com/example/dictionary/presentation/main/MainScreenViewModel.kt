package com.example.dictionary.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary.common.Resource
import com.example.dictionary.domain.use_cases.GetAllCachedWordInfos
import com.example.dictionary.presentation.screens.Screens
import com.example.dictionary.presentation.uievent.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getAllCachedWordInfos: GetAllCachedWordInfos
): ViewModel() {
    var isExpanded by mutableStateOf(false)

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents: MutableSharedFlow<UiEvent> = _uiEvents

    private val _state = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = _state

    init {
        getWords()
    }

    private fun getWords(){
        getAllCachedWordInfos().onEach { result ->
            when(result){
                is Resource.Error -> {
                    _state.value = MainScreenState(message = result.message?:"unknown error occurred")
                }
                is Resource.Loading -> {
                    _state.value = MainScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = MainScreenState(words = result.data?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(mainScreenEvents: MainScreenEvents){
        when(mainScreenEvents){
            MainScreenEvents.OnSearchClicked -> {
                viewModelScope.launch {
                    _uiEvents.emit(UiEvent.OnNavigate(Screens.SearchScreen.route))
                }
            }
        }
    }
}
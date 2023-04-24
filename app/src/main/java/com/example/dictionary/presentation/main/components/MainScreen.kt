package com.example.dictionary.presentation.main.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionary.presentation.main.MainScreenEvents
import com.example.dictionary.presentation.main.MainScreenViewModel
import com.example.dictionary.presentation.uievent.UiEvent
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.OnNavigate) -> Unit
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true){
        viewModel.uiEvents.collectLatest { events ->
            when(events){
                is UiEvent.OnNavigate -> {
                    onNavigate(events)
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Dictionary")
                },
                actions = {
                    IconButton(onClick = { viewModel.onEvent(MainScreenEvents.OnSearchClicked) }) {
                        Icon(imageVector = Icons.Rounded.Search, contentDescription = "search")
                    }
                }
            )
        }
    ) {
        Box{
            if (state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(modifier = Modifier.padding(top = it.calculateTopPadding())){
                    items(state.words){ word ->
                        SingleRow(
                            wordInfo = word,
                            isExpanded = viewModel.isExpanded,
                            onExpand = { viewModel.isExpanded = it}
                        )
                    }
                }
            }
        }
    }
}
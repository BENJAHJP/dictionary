package com.example.dictionary.presentation.main.components

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionary.SpeechRecognizer
import com.example.dictionary.presentation.main.MainScreenEvents
import com.example.dictionary.presentation.main.MainScreenViewModel
import com.example.dictionary.presentation.uievent.UiEvent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    val permission = rememberPermissionState(permission = Manifest.permission.RECORD_AUDIO)
    val speechRecognizer = rememberLauncherForActivityResult(
        contract = SpeechRecognizer(),
        onResult = {
            viewModel.onEvent(MainScreenEvents.OnSearchTextChanged(it.toString()))
        }
    )
    LaunchedEffect(key1 = true){
        permission.launchPermissionRequest()
    }

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collectLatest { event ->
            when(event){
                is UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
    }

    Scaffold() {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    value = viewModel.searchQuery.value,
                    onValueChange = viewModel::onSearch,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search ....")
                    },
                    trailingIcon = {
                        androidx.compose.material3.IconButton(onClick = {
                            if (permission.status.isGranted){
                                speechRecognizer.launch(Unit)
                            } else {
                                permission.launchPermissionRequest()
                            }
                        }) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Rounded.Call,
                                contentDescription = "speech recognition")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ){
                    items(state.wordInfoItems.size){ i ->
                        val wordInfo = state.wordInfoItems[i]
                        if (i > 0){
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        WordInfoItem(wordInfo = wordInfo)
                        if (i < state.wordInfoItems.size - 1){
                            Divider()
                        }
                    }
                }
            }
            if (state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
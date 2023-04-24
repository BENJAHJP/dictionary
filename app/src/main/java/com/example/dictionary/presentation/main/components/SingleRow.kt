package com.example.dictionary.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionary.domain.model.WordInfo

@Composable
fun SingleRow(
    wordInfo: WordInfo,
) {
    Column() {
        wordInfo.word?.let {
            Text(
                text = it,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        wordInfo.phonetic?.let { Text(text = it, fontWeight = FontWeight.Light) }
        Spacer(modifier = Modifier.height(16.dp))
        wordInfo.meanings?.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            meaning.definitions.forEachIndexed { i, definition ->
                Text(text = "${i + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let { example ->
                    Text(text = "Example: $example")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
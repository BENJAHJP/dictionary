package com.example.dictionary.domain.model

import com.example.dictionary.data.remote.dto.License
import com.example.dictionary.data.remote.dto.MeaningDto
import com.example.dictionary.data.remote.dto.PhoneticDto

data class WordInfo(
    val meanings: List<Meaning>? = emptyList(),
    val phonetic: String? = null,
    val word: String? = ""
)

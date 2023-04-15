package com.example.dictionary.domain.model

import com.example.dictionary.data.remote.dto.Definition

data class Meaning(
    val antonyms: List<Any>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)

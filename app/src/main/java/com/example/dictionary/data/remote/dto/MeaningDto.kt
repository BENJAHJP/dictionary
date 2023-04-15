package com.example.dictionary.data.remote.dto

data class MeaningDto(
    val antonyms: List<Any>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)
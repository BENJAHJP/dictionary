package com.example.dictionary.data.remote.dto

import com.example.dictionary.domain.model.WordInfo

data class WordInfoDto(
    val license: License,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
){
    fun toWordInfo(): WordInfo {
        return WordInfo(
            license = license,
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            word = word
        )
    }
}
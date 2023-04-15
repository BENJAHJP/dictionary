package com.example.dictionary.data.remote.dto

import com.example.dictionary.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<Any>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
){
    fun toMeaning(): Meaning {
        return Meaning(
            antonyms = antonyms,
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech,
            synonyms = synonyms
        )
    }
}
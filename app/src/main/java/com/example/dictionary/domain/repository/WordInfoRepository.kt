package com.example.dictionary.domain.repository

import com.example.dictionary.common.Resource
import com.example.dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
    fun getCachedWords(): Flow<Resource<List<WordInfo>>>
}
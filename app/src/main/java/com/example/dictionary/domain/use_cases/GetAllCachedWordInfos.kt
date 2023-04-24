package com.example.dictionary.domain.use_cases

import com.example.dictionary.common.Resource
import com.example.dictionary.domain.model.WordInfo
import com.example.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCachedWordInfos @Inject constructor(
    private val repository: WordInfoRepository
) {
    operator fun invoke(): Flow<Resource<List<WordInfo>>>{
        return repository.getCachedWords()
    }
}
package com.example.dictionary.domain.use_cases

import com.example.dictionary.common.Resource
import com.example.dictionary.domain.model.WordInfo
import com.example.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWordInfo constructor(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>>{
        if (word.isBlank()){
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}
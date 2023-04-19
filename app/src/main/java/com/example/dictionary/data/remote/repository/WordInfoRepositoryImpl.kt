package com.example.dictionary.data.remote.repository

import android.util.Log
import com.example.dictionary.common.Resource
import com.example.dictionary.data.local.WordInfoDao
import com.example.dictionary.data.remote.api.DictionaryApi
import com.example.dictionary.domain.model.WordInfo
import com.example.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WordInfoRepositoryImpl @Inject constructor(
    private val api: DictionaryApi,
    private val dao:WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException){
            emit(Resource.Error("Oops something went wrong", data = wordInfos))
        } catch (e: IOException){
            emit(Resource.Error("Check your internet connection", data = wordInfos))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}
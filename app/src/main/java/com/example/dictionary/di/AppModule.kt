package com.example.dictionary.di

import android.app.Application
import androidx.room.Room
import com.example.dictionary.common.Constants
import com.example.dictionary.data.local.GsonParser
import com.example.dictionary.data.local.WordInfoDao
import com.example.dictionary.data.local.database.WordInfoDatabase
import com.example.dictionary.data.remote.api.DictionaryApi
import com.example.dictionary.data.remote.repository.WordInfoRepositoryImpl
import com.example.dictionary.domain.repository.WordInfoRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase{
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_db"
        )
            .addTypeConverter(GsonParser(Gson()))
            .build()
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(dao: WordInfoDao, api: DictionaryApi): WordInfoRepository {
        return WordInfoRepositoryImpl(api, dao)
    }
}
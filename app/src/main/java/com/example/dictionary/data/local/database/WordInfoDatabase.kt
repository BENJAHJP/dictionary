package com.example.dictionary.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dictionary.data.local.WordInfoDao
import com.example.dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
abstract class WordInfoDatabase: RoomDatabase() {
    abstract val dao: WordInfoDao
}
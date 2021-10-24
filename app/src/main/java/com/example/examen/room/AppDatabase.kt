package com.example.examen.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.examen.models.entity.Movie
import com.example.examen.models.entity.Person
import com.example.examen.models.entity.Tv
import com.example.examen.room.converters.IntegerListConverter
import com.example.examen.room.converters.KeywordListConverter
import com.example.examen.room.converters.ReviewListConverter
import com.example.examen.room.converters.StringListConverter
import com.example.examen.room.converters.VideoListConverter

@Database(entities = [(Movie::class), (Tv::class), (Person::class)],
  version = 2, exportSchema = false)
@TypeConverters(value = [(StringListConverter::class), (IntegerListConverter::class),
  (KeywordListConverter::class), (VideoListConverter::class), (ReviewListConverter::class)])
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun tvDao(): TvDao
  abstract fun peopleDao(): PeopleDao
}

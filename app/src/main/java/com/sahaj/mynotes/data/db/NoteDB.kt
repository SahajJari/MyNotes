package com.sahaj.mynotes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDB : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {

        @Volatile
        private var instance: NoteDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {

            instance ?: buildDatabase(context).also {

                instance = it

            }

        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            NoteDB::class.java,
            "notedb"
        ).build()

    }

}
package edu.wccnet.testlivedata

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


@Database(entities = arrayOf(MovieItem::class), version = 1)
abstract class AppDatabase(context: Context) : RoomDatabase() {
    abstract fun movieItemDao(): MovieItemDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "word_database")
                        .build()
            return INSTANCE
        }
    }

}
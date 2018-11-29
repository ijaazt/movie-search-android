package edu.wccnet.testlivedata.ui.main

import android.arch.persistence.room.Room
import android.content.Context
import edu.wccnet.testlivedata.AppDatabase

class DatabaseRepository(context: Context) {
    private var instance: DatabaseRepository? = null
    private val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "movies"
    ).build()

    fun isInstance(context: Context): DatabaseRepository {
        if (instance == null) instance = DatabaseRepository(context)
        return instance as DatabaseRepository
    }
}
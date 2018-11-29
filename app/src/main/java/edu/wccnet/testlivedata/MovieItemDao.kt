package edu.wccnet.testlivedata

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface MovieItemDao {
    @Query("select * from movieitem")
    fun getSavedMovies(): LiveData<List<MovieItem>>

    @Insert
    fun saveMovie(movieItem: MovieItem)

    @Delete
    fun deleteMovie(movieItem: MovieItem)
}
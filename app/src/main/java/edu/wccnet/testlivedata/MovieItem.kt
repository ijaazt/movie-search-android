package edu.wccnet.testlivedata

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap
import java.io.Serializable

@Entity
data class MovieItem(
        @PrimaryKey
        private var imdbID: String,

        var title: String,
        var year: String,
        var poster: Bitmap,
        var rating: String,
        var release: String,
        var genre: String,
        var director: String,
        var writers: String,
        var actors: String,
        var plot: String,
        var language: String,
        var country: String,
        var awards: String,
        var type: String
) : Serializable


//    constructor(title: String, year: String, poster: Bitmap, rated: String, released: String, genre: String, director: String, writer: String, actors: String, plot: String, language: String, country: String, awards: String) {
//    }
//
//    constructor(title: String, poster: Bitmap, year: String, type: String, rated: String) {
//        this.title = title
//        this.poster = poster
//        this.year = year
//        this.type = type
//    }
//
//    override fun toString(): String {
//        return "MovieItem{" +
//                "title='" + title + '\''.toString() +
//                ", year='" + year + '\''.toString() +
//                ", poster='" + poster + '\''.toString() +
//                ", rated='" + rated + '\''.toString() +
//                ", released='" + released + '\''.toString() +
//                ", genre='" + genre + '\''.toString() +
//                ", director='" + director + '\''.toString() +
//                ", writer='" + writer + '\''.toString() +
//                ", actors='" + actors + '\''.toString() +
//                ", plot='" + plot + '\''.toString() +
//                ", language='" + language + '\''.toString() +
//                ", country='" + country + '\''.toString() +
//                ", awards='" + awards + '\''.toString() +
//                ", ratings=" + ratings +
//                '}'.toString()
//    }
//}

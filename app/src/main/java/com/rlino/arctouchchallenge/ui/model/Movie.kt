package com.rlino.arctouchchallenge.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by Lino on 3/7/2018.
 */
@Parcelize
data class Movie(
    val id: Int,
    val posterPath: String ,
    val overview: String,
    val releaseDate: Date,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backDropPath: String,
    val popularity: Float,
    val voteCount: Int,
    val voteAverage: Float,
    val genres: List<Genre>
) : Parcelable {
    companion object {
        val EXTRA_ARG = "movieextraarg"
    }

    fun getGenreNames(): String {
        return genres.fold(""){ acc, genre ->
            if(acc.isEmpty())
                return genre.name
            "$genre.name, $acc"
        }
    }
}
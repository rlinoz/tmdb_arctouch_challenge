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
    val releaseDate: String,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backDropPath: String,
    val popularity: Float,
    val voteCount: Int,
    val voteAverage: Float,
    val genre: String
) : Parcelable {
    companion object {
        val EXTRA_ARG = "movieextraarg"
    }

}
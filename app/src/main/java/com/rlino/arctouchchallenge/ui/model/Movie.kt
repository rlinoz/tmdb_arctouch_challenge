package com.rlino.arctouchchallenge.ui.model

import java.util.*

/**
 * Created by Lino on 3/7/2018.
 */
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
    val voteAverage: Float
)

// poster_path string or null
// adult boolean
// overview string
// release_date string
// genre_ids array[integer]
// id integer
// original_title string
// original_language string
// title string
// backdrop_path string or null
// popularity number
// vote_count integer
// video boolean
// vote_average number

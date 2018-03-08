package com.rlino.arctouchchallenge.repository.dto.response

import com.rlino.arctouchchallenge.ui.model.Movie
import java.util.*

/**
 * Created by Lino on 3/7/2018.
 */
data class MovieDTO(
        val id: Int,
        val posterPath: String,
        val overview: String,
        val releaseDate: Date,
        val originalTitle: String,
        val originalLanguage: String,
        val title: String,
        val backDropPath: String,
        val popularity: Float,
        val voteCount: Int,
        val voteAverage: Float,
        val adult: Boolean,
        val genreIds: List<Int>,
        val video: Boolean
) {
    fun toModel(): Movie {
        return Movie(id, posterPath, overview, releaseDate, originalTitle, originalLanguage,
                originalTitle, backDropPath, popularity, voteCount, voteAverage)
    }
}
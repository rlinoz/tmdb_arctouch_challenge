package com.rlino.arctouchchallenge.repository.dto.response

import com.google.gson.annotations.SerializedName
import com.rlino.arctouchchallenge.ui.model.Genre
import com.rlino.arctouchchallenge.ui.model.Movie
import java.util.*

/**
 * Created by Lino on 3/7/2018.
 */
data class MovieResponse(
        @SerializedName("page") val page: Int,
        @SerializedName("results") val results: List<MovieDTO>,
        @SerializedName("total_pages") val totalPages: Int,
        @SerializedName("total_results") val totalResults: Int
)

data class MovieDTO(
        @SerializedName("id") val id: Int,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("release_date") val releaseDate: Date,
        @SerializedName("original_title") val originalTitle: String,
        @SerializedName("original_language") val originalLanguage: String,
        @SerializedName("title") val title: String,
        @SerializedName("backdrop_path") val backDropPath: String?,
        @SerializedName("popularity") val popularity: Float?,
        @SerializedName("vote_count") val voteCount: Int,
        @SerializedName("vote_average") val voteAverage: Float,
        @SerializedName("adult") val adult: Boolean,
        @SerializedName("genre_ids") val genreIds: List<Int>,
        @SerializedName("video") val video: Boolean?
) {
    fun toModel(genres: List<Genre>): Movie {
        return Movie(id, posterPath.orEmpty(), overview.orEmpty(), releaseDate, originalTitle, originalLanguage,
                originalTitle, backDropPath.orEmpty(), popularity ?: 0F, voteCount, voteAverage, genres)
    }

    fun findGenres(genres: List<GenreDTO>): List<Genre> {
        return genres.filter { genreIds.contains(it.id) }.map { it.toModel() }
    }
}
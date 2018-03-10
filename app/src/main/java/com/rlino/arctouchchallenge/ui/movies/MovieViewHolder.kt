package com.rlino.arctouchchallenge.ui.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import com.rlino.arctouchchallenge.ui.extesions.loadUrl
import com.rlino.arctouchchallenge.ui.model.Movie
import kotlinx.android.synthetic.main.movie_vh.view.*

/**
 * Created by Lino on 3/8/2018.
 */
class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) {

        itemView.apply {
            title.text = movie.title
            genre.text = movie.genre
            releaseDate.text = movie.releaseDate
            image.loadUrl("https://image.tmdb.org/t/p/w92/${movie.posterPath}")
        }

    }

}
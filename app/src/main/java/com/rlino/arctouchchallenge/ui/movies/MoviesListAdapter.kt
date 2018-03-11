package com.rlino.arctouchchallenge.ui.movies

import android.support.v4.view.PagerAdapter.POSITION_NONE
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rlino.arctouchchallenge.R
import com.rlino.arctouchchallenge.ui.extesions.inflate
import com.rlino.arctouchchallenge.ui.model.Movie
import kotlin.properties.Delegates


/**
 * Created by Lino on 3/8/2018.
 */
class MoviesListAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    var movies: List<Movie> by Delegates.observable(emptyList()) {
        prop, old, new ->
        DiffUtil.calculateDiff(MoviesDiffUtilCallback(old, new)).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieItemView = parent.inflate(R.layout.movie_vh)

        val vh = MovieViewHolder(movieItemView)
        vh.itemView.setOnClickListener {
            val pos = vh.adapterPosition
            if(pos != POSITION_NONE) {
                onItemClickListener.onClick(movies[pos])
            }
        }

        return vh
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    interface OnItemClickListener {
        fun onClick(movie: Movie)
    }
}
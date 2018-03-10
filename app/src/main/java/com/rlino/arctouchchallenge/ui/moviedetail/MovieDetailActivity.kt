package com.rlino.arctouchchallenge.ui.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rlino.arctouchchallenge.ui.model.Movie

/**
 * Created by Lino on 3/8/2018.
 */
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        fun newIntent(from: Context, movie: Movie): Intent {
            val intent = Intent(from, this::class.java)
            intent.putExtra(Movie.EXTRA_ARG, movie)
            return intent;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

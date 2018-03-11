package com.rlino.arctouchchallenge.ui.moviedetail

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.view.MenuItem
import android.view.View
import com.rlino.arctouchchallenge.R
import com.rlino.arctouchchallenge.ui.extesions.loadUrl
import com.rlino.arctouchchallenge.ui.model.Movie
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.activity_movie_detail.*


/**
 * Created by Lino on 3/8/2018.
 */
class MovieDetailActivity : AppCompatActivity() {

    lateinit var movie: Movie

    companion object {
        fun start(from: AppCompatActivity, movie: Movie) {
            val intent = Intent(from, MovieDetailActivity::class.java)
            intent.putExtra(Movie.EXTRA_ARG, movie)
            from.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setupFromIntent()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        collapsingToolbar.title = movie.title
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))

        render()
    }

    private fun setupFromIntent() {
        if(intent.hasExtra(Movie.EXTRA_ARG)) {
            movie = intent.getParcelableExtra(Movie.EXTRA_ARG)
        } else {
            finish()
        }
    }

    private fun applyPalette(palette: Palette) {
        val primaryDark = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        val primary = ContextCompat.getColor(this, R.color.colorPrimary)
        collapsingToolbar.setContentScrimColor(palette.getMutedColor(primary))
        collapsingToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark))
        supportStartPostponedEnterTransition()
    }

    private fun render() {
        name.text = movie.title
        genre.text = movie.genre
        releaseDate.text = movie.releaseDate
        image.loadUrl("https://image.tmdb.org/t/p/w92/${movie.posterPath}")
        movieBackDrop.loadUrl("https://image.tmdb.org/t/p/w780/${movie.backDropPath}", object: Callback {
            override fun onSuccess() {
                val bitmap = (image.drawable as BitmapDrawable).bitmap
                Palette.from(bitmap).generate(this@MovieDetailActivity::applyPalette)
            }

            override fun onError() {

            }
        })
        overview.text = movie.overview
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

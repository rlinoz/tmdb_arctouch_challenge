package com.rlino.arctouchchallenge

import android.app.Application
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso



/**
 * Created by Lino on 3/9/2018.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, 1024 * 10))
        val built = builder.build()
        built.setIndicatorsEnabled(true)
        built.isLoggingEnabled = true
        Picasso.setSingletonInstance(built)
    }
}
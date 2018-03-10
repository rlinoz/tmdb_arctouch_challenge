package com.rlino.arctouchchallenge.ui.extesions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Lino on 3/8/2018.
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)


fun ImageView.loadUrl(url: String) =
    Picasso.with(context).load(url).into(this)

package com.rlino.arctouchchallenge.ui.extesions

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Lino on 3/8/2018.
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)


fun ImageView.loadUrl(url: String) =
    Picasso.with(context).load(url).into(this)

fun ImageView.loadUrl(url: String, callback: Callback) =
        Picasso.with(context).load(url).into(this, callback)

fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Date.toFormattedString(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    return sdf.format(this)
}
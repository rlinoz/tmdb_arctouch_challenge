package com.rlino.arctouchchallenge.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Lino on 3/11/2018.
 */
@Parcelize
class Genre(
        val id: Int,
        val name: String
) : Parcelable
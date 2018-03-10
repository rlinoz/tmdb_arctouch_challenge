package com.rlino.arctouchchallenge.ui.movies

import android.support.v7.util.DiffUtil
import com.rlino.arctouchchallenge.ui.model.Movie

/**
 * Created by Lino on 3/8/2018.
 */
class MoviesDiffUtilCallback(
        val oldList: List<Movie>,
        val newList: List<Movie>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
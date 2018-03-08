package com.rlino.arctouchchallenge.repository

import retrofit2.Retrofit

/**
 * Created by Lino on 3/7/2018.
 */
object RetrofitProvider {
    fun get(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("")
                .build()
    }
}
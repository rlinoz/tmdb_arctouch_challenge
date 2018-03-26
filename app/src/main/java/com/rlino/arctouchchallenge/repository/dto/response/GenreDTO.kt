package com.rlino.arctouchchallenge.repository.dto.response

import com.google.gson.annotations.SerializedName
import com.rlino.arctouchchallenge.ui.model.Genre

/**
 * Created by Lino on 3/11/2018.
 */
class GenreResponse(
        @SerializedName("genres") val genres: List<GenreDTO>
)

class GenreDTO(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
) {

    fun toModel(): Genre {
        return Genre(id, name)
    }

}
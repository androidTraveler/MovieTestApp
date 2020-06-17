package com.test.movietestapp.data.model.response

import com.google.gson.annotations.SerializedName

class GenresResponse {
    @SerializedName("genres")
    var genres: List<Genre?>? = null

    class Genre {
        @SerializedName("id")
        var id: Int? = null

        @SerializedName("name")
        var name: String? = null
    }
}
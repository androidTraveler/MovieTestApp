package com.test.movietestapp.data.repository.local

interface LocalDataRepository {
    fun saveLocallyGenres(genres: Map<Int, String>)
    fun getLocalGenre(id: Int?): String?
}
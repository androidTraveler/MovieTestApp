package com.test.movietestapp.data.repository.local

import android.content.Context
import com.orhanobut.hawk.Hawk
import com.test.movietestapp.data.constants.Constants

class LocalDataRepositoryImpl(context: Context) : LocalDataRepository {
    init {
        Hawk.init(context).build()
    }

    override fun saveLocallyGenres(genres: Map<Int, String>) {
        var map = Hawk.get<MutableMap<Int, String>>(Constants.EXTRA_GENRE_MAP)
        if (map == null) map = mutableMapOf()
        map.putAll(genres)
        Hawk.put(Constants.EXTRA_GENRE_MAP, map)
    }

    override fun getLocalGenre(id: Int?): String? {
        val map = Hawk.get<MutableMap<Int, String>>(Constants.EXTRA_GENRE_MAP)
        return map[id]
    }

}
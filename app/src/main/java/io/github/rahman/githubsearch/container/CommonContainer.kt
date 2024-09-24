package io.github.rahman.githubsearch.container

import android.content.Context
import android.content.SharedPreferences
import io.github.rahman.githubsearch.base.Cache


internal class CommonContainer (context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("github_search_cache", Context.MODE_PRIVATE)
    }

    val cache: Cache by lazy {
        Cache(sharedPreferences)
    }
}
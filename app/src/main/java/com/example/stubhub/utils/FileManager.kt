package com.example.stubhub.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class FileManager @Inject constructor(@ApplicationContext val context: Context) {

    fun readFromAsset(assetName: String) = context.assets
        .open(assetName)
        .bufferedReader()
        .use { it.readText() }
}
package com.luxoft.data.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object ReadJsonFileUtils {

    fun loadJsonFileFromResources(path: String): String {
        try {
            val sb = StringBuilder()
            val reader = BufferedReader(InputStreamReader(
                    javaClass.classLoader?.getResourceAsStream(path), "UTF-8"))
            var line: String?
            while (true) {
                line = reader.readLine()
                if (line != null) sb.append(line)
                else break
            }
            return sb.toString()
        } catch (e: IOException) {
            throw IllegalArgumentException("Could not read from resource at: $path")
        }
    }
}
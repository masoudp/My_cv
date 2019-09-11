package com.mpakbaz.mycv.util

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException

class JsonUtils {

    companion object {
        fun  prettyJson(string: String): String? {
            var string = string
            try {
                val parser = JsonParser()
                val gson = GsonBuilder().setPrettyPrinting().create()
                val el = parser.parse(string)
                string = gson.toJson(el) // done
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
            }

            return string
        }

    }


}

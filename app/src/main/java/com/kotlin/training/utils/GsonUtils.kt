package com.kotlin.training.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser


class GsonUtils(element: JsonElement) {
    var gson = Gson()
    var prettyGson = GsonBuilder().setPrettyPrinting()
            .create()
    var jsonParser = JsonParser()

    var json: JsonElement = gson.fromJson(element, JsonElement::class.java)
}
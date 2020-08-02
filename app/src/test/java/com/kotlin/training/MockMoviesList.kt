package com.kotlin.training

import com.kotlin.training.data.room.entity.Movie


class MockMoviesList {

    var page: Int? = 1
    var results: List<Movie> = listOf(abc)
    var totalResults: Int? = 1
    var totalPages: Int? = 1
}

var abc: Movie = Movie(1, 1, "", false, "Parasite", "",
        "Parasaite", "", "Parasite", "", 4.0f, 4,false, 4.0f)
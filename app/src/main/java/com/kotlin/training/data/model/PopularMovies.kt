package com.kotlin.training.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.kotlin.training.data.room.entity.Movie



class PopularMovies {
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Movie> =
        ArrayList()
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("status_message")
    @Expose
    var statusMessage: String? = null
    @SerializedName("errors")
    @Expose
    var errors: String? = null

}

package com.kotlin.training.repository

import com.kotlin.training.R

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    EMPTY,
    REQUEST

}

class ErrorState(val status: Status, val msg: Int) {

    companion object {

        val LOADED: ErrorState
        val LOADING: ErrorState
        val ERROR: ErrorState
        val EMPTY: ErrorState
        val REQUEST: ErrorState

        init {
            LOADED = ErrorState(Status.SUCCESS, R.string.success)

            LOADING = ErrorState(Status.RUNNING, R.string.running)

            ERROR = ErrorState(Status.FAILED, R.string.network_error)

            EMPTY = ErrorState(Status.EMPTY, R.string.empty)

            REQUEST = ErrorState(Status.REQUEST, R.string.request)

        }
    }
}
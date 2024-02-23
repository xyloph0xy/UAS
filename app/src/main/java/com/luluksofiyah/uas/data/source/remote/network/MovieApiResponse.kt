package com.luluksofiyah.uas.data.source.remote.network

sealed class MovieApiResponse<out R> {
    data class Success<out T>(val data: T) : MovieApiResponse<T>()
    data class Error(val errorMessage: String) : MovieApiResponse<Nothing>()
    data object Empty : MovieApiResponse<Nothing>()
}
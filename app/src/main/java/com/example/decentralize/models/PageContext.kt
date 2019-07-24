package com.example.decentralize.models


import com.google.gson.annotations.SerializedName

data class PageContext(
    val fetchAllPages: Boolean,
    val pageNumber: Int,
    val paginatedFetch: Boolean,
    val paginationContextMap: PaginationContextMap
)
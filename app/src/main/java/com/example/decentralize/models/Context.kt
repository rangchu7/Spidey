package com.example.decentralize.models


import com.google.gson.annotations.SerializedName

data class Context(
    val collectionMetaInfoList: List<Any>,
    val paginationCursor: Any
)
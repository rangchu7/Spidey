package com.example.decentralize.models


import com.google.gson.annotations.SerializedName

data class configData(
    @SerializedName("entry_id")
    val entryId: String,
    @SerializedName("job_id")
    val jobId: String,
    val placeholders: List<String>,
    val priority: Int,
    val url: String
)
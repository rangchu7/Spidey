package com.example.decentralize.models


import com.google.gson.annotations.SerializedName

data class jobConfig(
    @SerializedName("base_url")
    val baseUrl: String,
    val countPH: Int,
    @SerializedName("job_id")
    val jobId: String,
    val method: String,
    val postFields: PostFields,
    val reqType: Int
)
package com.example.searchjobs

import com.google.gson.annotations.SerializedName

class Job (
    @SerializedName("tittle")
    val title: String,

    @SerializedName("company")
    val company: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("company_logo")
    val companyLogo: String
)
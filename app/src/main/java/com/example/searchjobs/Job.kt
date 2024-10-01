package com.example.searchjobs

import com.google.gson.annotations.SerializedName

class Job (
    @SerializedName("")
    val title: String,

    @SerializedName("")
    val company: String,

    @SerializedName("")
    val description: String,

    @SerializedName("company_logo")
    val companyLogo: String
)
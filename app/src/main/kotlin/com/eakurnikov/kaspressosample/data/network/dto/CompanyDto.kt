package com.eakurnikov.kaspressosample.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by eakurnikov on 2019-12-15
 */
data class CompanyDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("catchPhrase")
    val catchPhrase: String,

    @SerializedName("bs")
    val bs: String
)
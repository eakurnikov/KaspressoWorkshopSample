package com.eakurnikov.kaspressosample.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by eakurnikov on 2019-12-15
 */
data class GeoDto(

    @SerializedName("lat")
    val lat: String,

    @SerializedName("lng")
    val lng: String
)
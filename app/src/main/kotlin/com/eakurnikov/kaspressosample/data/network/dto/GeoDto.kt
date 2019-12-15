package com.eakurnikov.kaspressosample.data.network.dto

import com.google.gson.annotations.SerializedName

data class GeoDto(

    @SerializedName("lat")
    val lat: String,

    @SerializedName("lng")
    val lng: String
)
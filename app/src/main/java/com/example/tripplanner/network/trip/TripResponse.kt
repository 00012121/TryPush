package com.example.tripplanner.network.trip

import com.google.gson.annotations.SerializedName

data class TripResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val city: String,
    @SerializedName("text_list")
    val rating: String,
    @SerializedName("color")
    val cost: String
)
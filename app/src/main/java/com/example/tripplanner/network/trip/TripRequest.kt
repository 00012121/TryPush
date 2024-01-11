package com.example.tripplanner.network.trip

import com.google.gson.annotations.SerializedName

data class TripRequest(
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val city: String,
    @SerializedName("phone")
    val rating: String,
    @SerializedName("color") //I keep my rating value inside a "color" field in the database because of the test API limitations
    val cost: String
)
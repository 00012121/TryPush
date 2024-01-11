package com.example.tripplanner.models

//import com.example.tripplanner.network.trip.TripResponseCityItem
import com.google.gson.annotations.SerializedName

data class Trip(
    val id: Int,
    val name: String,
    val city: String,
    val rating: String,
    val cost: String

)

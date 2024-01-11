package com.example.tripplanner.network

import com.example.tripplanner.network.trip.TripRequest
import com.example.tripplanner.network.trip.TripResponse
import com.example.tripplanner.network.myResponse.MyItemResponse
import com.example.tripplanner.network.myResponse.MyListResponse
import com.example.tripplanner.network.myResponse.MyResponse
import retrofit2.http.*

interface TripService {
    @GET("records/all")
    suspend fun getAllTrips(
        @Query("student_id") student_id: String
    ): MyListResponse<TripResponse>

    @GET("records/{record_id}")
    suspend fun getOneTripById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<TripResponse>

    @POST("records")
    suspend fun insertNewTrip(
        @Query("student_id") student_id: String,
        @Body Request: TripRequest
    ): MyResponse

    @PUT("records/{record_id}")
    suspend fun updateOneTripById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String,
        @Body movieRequest: TripRequest
    ): MyResponse

    @DELETE("records/{record_id}")
    suspend fun deleteOneTripById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyResponse

    @DELETE("records/all")
    suspend fun deleteAllTrips(
        @Query("student_id") student_id: String
    ): MyResponse
}
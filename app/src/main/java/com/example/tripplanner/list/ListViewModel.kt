package com.example.tripplanner.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripplanner.network.trip.TripResponse
import com.example.tripplanner.models.Trip
import com.example.tripplanner.network.myResponse.MyListResponse
import com.example.tripplanner.network.RetrofitInstance
import com.example.tripplanner.utils.Constants
//import com.example.tripplanner.utils.extractListOfCitiesFromResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class ListViewModel : ViewModel() {
    val tripsLiveData: MutableLiveData<List<Trip>> by lazy {
        MutableLiveData<List<Trip>>()
    }

    init {
        getListOfTripsFromRemoteDb()
        //deleteAllTrips()
    }

    fun getListOfTripsFromRemoteDb() {
        viewModelScope.launch {
            try {
                val response: MyListResponse<TripResponse> =
                    RetrofitInstance.tripService.getAllTrips(Constants.STUDENT_ID)
                val tripsFromResponse = response.data

                if (tripsFromResponse != null) {
                    val myTrips = mutableListOf<Trip>()

                    for (tripFromResponse in tripsFromResponse) {
                        myTrips.add(
                            Trip(
                                tripFromResponse.id,
                                tripFromResponse.name,
                                tripFromResponse.city,
                                tripFromResponse.rating,
                                tripFromResponse.cost
                            )
                        )
                    }

                    tripsLiveData.value = myTrips
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

}
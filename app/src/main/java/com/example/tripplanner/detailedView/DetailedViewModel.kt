package com.example.mynavigationsample.detailedView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripplanner.network.trip.TripRequest
import com.example.tripplanner.network.myResponse.MyResponse
import com.example.tripplanner.models.Trip
import com.example.tripplanner.network.RetrofitInstance
import com.example.tripplanner.network.myResponse.MyItemResponse
import com.example.tripplanner.network.trip.TripResponse
import com.example.tripplanner.utils.Constants
//import com.example.tripplanner.utils.extractListOfCitiesFromResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailedViewModel : ViewModel() {
    fun getTripByIdFromRemoteDb(tripId: String): Trip {
        return Trip(
            1,
            "Hollywood Stay",
            "Los Angeles, California",
             "7",
            "350"
        )
    }
}
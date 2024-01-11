package com.example.tripplanner.addNew

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripplanner.network.trip.TripRequest
import com.example.tripplanner.network.myResponse.MyResponse
import com.example.tripplanner.network.RetrofitInstance
import com.example.tripplanner.utils.Constants
import kotlinx.coroutines.launch

class AddNewViewModel: ViewModel() {

    val tripInsertResponse: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    fun saveNewTripToRemoteDb(trip: TripRequest) {

        viewModelScope.launch {
            try {

                val response: MyResponse = RetrofitInstance.tripService.insertNewTrip(
                    Constants.STUDENT_ID,
                    trip
                )

                tripInsertResponse.value = response

                Log.d("Update_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

}
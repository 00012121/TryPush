package com.example.tripplanner.network.myResponse

import com.google.gson.annotations.SerializedName

class MyItemResponse <T> (@SerializedName("data")
                          val data: T?) : MyResponse()
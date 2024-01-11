package com.example.tripplanner.list

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripplanner.addNew.AddNewTrip
import com.example.tripplanner.models.Trip
import com.example.tripplanner.R
import java.util.jar.Attributes.Name

@Composable
fun TripsList(
    onTripClick: (String) -> Unit = {},
    viewModel: ListViewModel = ListViewModel()
) {

    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {

        val trips by viewModel.tripsLiveData.observeAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    colorResource(R.color.trip_list_bg)
                )
                .padding(0.dp, 0.dp, 0.dp, 50.dp),
        ) {
            trips?.let {
                items(items = it.toList(), itemContent = { item ->
                    TripItem(trip = item, onTripClick)
                })
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp, 0.dp, 0.dp, 60.dp),
            onClick = { context.startActivity(Intent(context, AddNewTrip::class.java)) }) {
            Text(
                stringResource(id = R.string.list_add_new_text),
                modifier = Modifier.padding(30.dp, 25.dp),
                color = colorResource(id = R.color.add_new_trip_text_color)
            )
        }
    }

}

@Composable
fun TripItem(trip: Trip, onTripClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 10.dp, 15.dp, 0.dp)
            .clickable {
                onTripClick(trip.id.toString())
            }
    ) {
        Name(name = trip.name)
        City(city = trip.city)
        Divider(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(Color.Gray)
        )
    }
}

@Composable
private fun Name(name: String) {
    Text(
        text = name,
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun City(city: String) {
    Text(
        text = city,
        color = Color.DarkGray,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        TripsList()
}


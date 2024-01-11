package com.example.tripplanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynavigationsample.detailedView.DetailedViewModel

@Composable
fun DetailedView(tripId: String, viewModel: DetailedViewModel = DetailedViewModel()) {

    val trip = viewModel.getTripByIdFromRemoteDb(tripId)


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.trip_detailed_view_bg))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Name(name = trip.name)
            }
            MyDivider()
            Spacer(Modifier.height(16.dp))
            City(city = trip.city)
            MyDivider(  )
            Spacer(Modifier.height(16.dp))
            Rating(rating = trip.rating)
            MyDivider()
            Spacer(Modifier.height(16.dp))
            Cost(cost = trip.cost)


        }

}

@Composable
private fun Name(name: String) {
    Text(
        text = name,
        modifier = Modifier.padding(bottom = 30.dp),
        color = Color.Green,
        fontSize = 45.sp,
        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center
    )
}


@Composable

private fun City(city: String) {
    Text(
        modifier = Modifier.padding(bottom = 1.dp),
        text = city,
        color = Color.DarkGray,
        fontSize = 30.sp,
        fontFamily = FontFamily.SansSerif
    )
}



@Composable

private fun Rating(rating: String) {
    Text(
        modifier = Modifier.padding(bottom = 1.dp),
        text = stringResource(id = R.string.detailed_view_rating_label, rating),
        color = Color.DarkGray,
        fontSize = 30.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
private fun Cost(cost: String) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.detailed_view_cost_label, cost),
        color = Color.DarkGray,
        fontSize = 30.sp,
        fontFamily = FontFamily.SansSerif
    )
}


@Composable
private fun MyDivider() {
    Divider(
        color = Color.LightGray,
        thickness = 5.dp
    )
}




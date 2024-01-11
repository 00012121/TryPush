package com.example.tripplanner.addNew

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.tripplanner.network.trip.TripRequest
import com.example.tripplanner.MainActivity
import com.example.tripplanner.R
import com.example.tripplanner.network.myResponse.MyResponse
//import com.example.tripplanner.utils.parseCitiesFromInput


@Composable
fun AddNewView(viewModel: AddNewViewModel = AddNewViewModel()) {
    val context = LocalContext.current

    val name = remember{ mutableStateOf("")}
    val city = remember { mutableStateOf("") }
    val rating = remember { mutableStateOf("") }
    val cost = remember { mutableStateOf("") }

    val isProgressVisible = remember { mutableStateOf(false) }

    val response by viewModel.tripInsertResponse.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        NameInput(name = name.value, onNameChange = {name.value = it})
        Spacer(modifier = Modifier.height(16.dp))
        CityInput(
            city = city.value ,
            onCityChange ={city.value = it}
        )
        Spacer(modifier = Modifier.height(16.dp))
        RatingInput(rating = rating.value ,
            onRatingChange ={rating.value = it})
        Spacer(modifier = Modifier.height(16.dp))
        CostInput(cost = cost.value ,
            onCostChange ={cost.value = it})
        Spacer(modifier = Modifier.height(200.dp))
        AddNewButton {
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
        }
        //validation
        val validationMsg = stringResource(id = R.string.add_new_validation_msg)
        AddNewButton {
            if (isInputValid(name.value, city.value, rating.value, cost.value)) {
                viewModel.saveNewTripToRemoteDb(
                    TripRequest(
                        name.value,
                        city.value,
                        rating.value,
                        cost.value
                    )
                )

                isProgressVisible.value = true

            } else {
                val toast = Toast.makeText(context, validationMsg, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }

    }
    response?.let { ProgressWidget(response = it, isVisible = isProgressVisible.value, context) }
}


??
@Composable
private fun ProgressWidget(response: MyResponse, isVisible: Boolean, context: Context) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Text(
                modifier = Modifier
                    .background(colorResource(id = R.color.add_new_trip_text_color))
                    .padding(20.dp)
                    .align(Alignment.Center),
                fontSize = 25.sp,
                text =
                if (response.status.isEmpty()) stringResource(id = R.string.add_new_in_progress_mgs) //by default status is "", so if it is empty that means network request hasn't returned a response yet
                else if (response.status == "OK") stringResource(id = R.string.add_new_saved_successfully_msg)
                else stringResource(id = R.string.add_new_failed_to_save_msg)
            )
        }

        context.startActivity(Intent(context, MainActivity::class.java))
    }
}


@Composable
private fun NameInput(name: String, onNameChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray),
        value = name,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onNameChange(it) },
        label = {
            Text(stringResource(id = R.string.add_new_name_input_hint))
        }
    )
}

@Composable
private fun CityInput(city: String, onCityChange: (String) -> Unit) {

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(color = Color.LightGray),
        value = city,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onCityChange(it) },
        label = {
            Text(stringResource(id = R.string.add_new_city_input_hint))
        }
    )
}

@Composable
private fun RatingInput(rating: String, onRatingChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray),
        value = rating,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { onRatingChange(it)},
        label = {
            Text(stringResource(id = R.string.add_new_rating_input_hint))
        }
    )
}

@Composable
private fun CostInput(cost: String, onCostChange: (String) -> Unit) {

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray),
        value = cost,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { onCostChange(it) },
        label = {
            Text(stringResource(id = R.string.add_new_cost_input_hint))
        }
    )
}

@Composable
private fun AddNewButton(onClick: () -> Unit) {

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.add_new_save_button_text)
        )
    }
}
??
private fun isInputValid(
    name: String,
    city: String,
    rating: String,
    cost: String
): Boolean {
    if (name.isBlank() || city.isBlank() || cost.isBlank() || rating.isBlank()) return false

    if (city.isBlank() || !city.isDigitsOnly()) return false

    return true
}

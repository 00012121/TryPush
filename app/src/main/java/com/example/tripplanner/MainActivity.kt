package com.example.tripplanner

import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tripplanner.ui.theme.TripPlannerTheme
import com.example.tripplanner.list.TripsList
import com.example.tripplanner.settings.Settings

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TripPlannerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Navigation through pages
                    val navController = rememberNavController()
                    Scaffold(bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = stringResource(id = R.string.navigation_trips_title),
                                    route = "tripsList",
                                    icon = Icons.Default.List
                                ),
                                BottomNavItem(
                                    name = stringResource(id = R.string.navigation_settings_title),
                                    route = "settings",
                                    icon = Icons.Default.Settings
                                )
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }

                        )
                    }) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}


@Composable
//pages
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "tripsList"){
        composable("tripsList") {
            TripsList(
                onTripClick ={tripId ->
                    navController.navigate("detailedView/$tripId")
                }
            )
        }

        composable("settings") {
            Settings()
        }

        composable(
            "detailedView/{tripId}"
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("tripId")?.let { DetailedView(it) }
        }
    }


}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit

) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = item.name)
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp
                        )
                    }
                }
            )
        }
    }

}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview(){
//    TripPlannerTheme {
//    }
//}
//


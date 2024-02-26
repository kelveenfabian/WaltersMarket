package com.v.waltersmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.v.waltersmarket.data.requestdata.WesternHoroscopeRequestData
import com.v.waltersmarket.ui.theme.WaltersMarketTheme
import com.v.waltersmarket.ui.viewmodel.AstrologyViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel = AstrologyViewModelFactory().getAstrologyViewModel()

    private val requestData = WesternHoroscopeRequestData(
        day = "23",
        month = "06",
        year = "1995",
        lon = "77.43",
        lat = "18.63",
        tzone = "5.5",
        hour = "12",
        min = "50",
    )

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchData()
        setContent {
            WaltersMarketTheme {
                val data = viewModel.planets.collectAsStateWithLifecycle()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var dayText by remember { mutableStateOf("") }
                    var monthText by remember { mutableStateOf("") }
                    var yearText by remember { mutableStateOf("") }
                    var lonText by remember { mutableStateOf("") }
                    var latText by remember { mutableStateOf("") }
                    var hourText by remember { mutableStateOf("") }
                    var minText by remember { mutableStateOf("") }
                    var timeZoneText by remember { mutableStateOf("") }
                   Column(
                       modifier = Modifier.fillMaxSize()
                   ) {
                       Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                           Column {
                               TextField(value = dayText, onValueChange = { dayText = it }, label = { Text("Day") }, maxLines = 1)
                               TextField(value = monthText, onValueChange = { monthText = it }, label = { Text("Month") }, maxLines = 1)
                               TextField(value = yearText, onValueChange = { yearText = it }, label = { Text("Year") }, maxLines = 1)
                               TextField(value = hourText, onValueChange = { hourText = it }, label = { Text("Hour") }, maxLines = 1)
                               TextField(value = minText, onValueChange = { minText = it }, label = { Text("Min") }, maxLines = 1)
                               TextField(value = lonText, onValueChange = { lonText = it }, label = { Text("Longitude") }, maxLines = 1)
                               TextField(value = latText, onValueChange = { latText = it }, label = { Text("Latitude") }, maxLines = 1)
                               TextField(value = timeZoneText, onValueChange = { timeZoneText = it }, label = { Text("TimeZone") }, maxLines = 1)
                           }
                       }


                       data.value.forEach {
                           Text(text = it.name!!)
                       }
                   }
                }
            }
        }
    }

    private fun fetchData() {
        viewModel.fetchWesternHoroscopeData(requestData)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WaltersMarketTheme {
        Greeting("Android")
    }
}
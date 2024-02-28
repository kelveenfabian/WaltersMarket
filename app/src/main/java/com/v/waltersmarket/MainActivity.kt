package com.v.waltersmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.v.waltersmarket.ui.theme.WaltersMarketTheme
import com.v.waltersmarket.ui.viewmodel.AstrologyViewModel
import com.v.waltersmarket.ui.viewmodel.AstrologyViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel = AstrologyViewModelFactory().getAstrologyViewModel()
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaltersMarketTheme {
                val planets = viewModel.planets.collectAsStateWithLifecycle()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Column(
                       modifier = Modifier.fillMaxSize()
                   ) {
                       Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                           Column {
                               UserInput(viewModel = viewModel)
                               Button(onClick = { viewModel.fetchWesternHoroscopeData() }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                   Text(text = "Fetch Data")
                               }
                           }
                       }

                       planets.value.forEach {
                           Text(text = it.name!!, modifier = Modifier.align(Alignment.CenterHorizontally))
                       }
                   }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInput(viewModel: AstrologyViewModel) {
    TextField(value = viewModel.dayText, onValueChange = { dayText -> viewModel.updateDay(dayText) }, label = { Text("Day") }, maxLines = 1)
    TextField(value = viewModel.monthText, onValueChange = { monthText -> viewModel.updateMonth(monthText) }, label = { Text("Month") }, maxLines = 1)
    TextField(value = viewModel.yearText, onValueChange = { yearText -> viewModel.updateYear(yearText) }, label = { Text("Year") }, maxLines = 1)
    TextField(value = viewModel.hourText, onValueChange = { hourText -> viewModel.updateHour(hourText) }, label = { Text("Hour") }, maxLines = 1)
    TextField(value = viewModel.minText, onValueChange = { minText -> viewModel.updateMinute(minText) }, label = { Text("Min") }, maxLines = 1)
    TextField(value = viewModel.lonText, onValueChange = { lonText -> viewModel.updateLongitude(lonText) }, label = { Text("Longitude") }, maxLines = 1)
    TextField(value = viewModel.latText, onValueChange = { latText -> viewModel.updateLatitude(latText) }, label = { Text("Latitude") }, maxLines = 1)
    TextField(value = viewModel.timeZoneText, onValueChange = { tzone -> viewModel.updateTimezone(tzone) }, label = { Text("TimeZone") }, maxLines = 1)
}
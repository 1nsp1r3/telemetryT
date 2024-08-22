package org.inspir3.telemetryt.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.inspir3.telemetryt.AppViewModel
import org.inspir3.telemetryt.Format

@Preview(showBackground = false)
@Composable
fun MainView(
    viewModel: AppViewModel = AppViewModel(),
    resetRoute: () -> Unit = {},
    exitRoute: () -> Unit = {},
) {
    //View will automatically refresh when these values change
    val temperatureMin: Int by viewModel.temperatureMin.observeAsState(initial = 0)
    val temperatureMax: Int by viewModel.temperatureMax.observeAsState(initial = 0)
    val temperatureActual: Int by viewModel.temperatureActual.observeAsState(initial = 0)

    val pressureMin: Int by viewModel.pressureMin.observeAsState(initial = 0)
    val pressureMax: Int by viewModel.pressureMax.observeAsState(initial = 0)
    val pressureActual: Int by viewModel.pressureActual.observeAsState(initial = 0)

    Column(Modifier.fillMaxWidth()) {
        Row {
            Text(
                text = "TelemetryT 0.1.0",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Row {
            ChartLegendView(
                min = Format.getTemperatureAsText(temperatureMin.toShort()),
                actual = Format.getTemperatureAsText(temperatureActual.toShort()),
                max = Format.getTemperatureAsText(temperatureMax.toShort()),
                legend = "°C",
                color = Color.Yellow,
            )
        }
        Row {
            ChartLegendView(
                min = Format.getPressureAsText(pressureMin.toShort()),
                actual = Format.getPressureAsText(pressureActual.toShort()),
                max = Format.getPressureAsText(pressureMax.toShort()),
                legend = " bars",
                color = Color.Cyan,
            )
        }
        Row {
            Button(
                onClick = { resetRoute() },
                modifier = Modifier.padding(0.dp, 0.dp, 16.dp, 0.dp),
            ) {
                Text("Reset")
            }
            Button(
                onClick = { exitRoute() },
            ) {
                Text("Exit")
            }
        }
    }
}

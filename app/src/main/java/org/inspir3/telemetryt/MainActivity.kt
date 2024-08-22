package org.inspir3.telemetryt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import org.inspir3.telemetryt.ui.theme.TelemetryTTheme
import org.inspir3.common.I3
import org.inspir3.common.ble.Gap
import org.inspir3.telemetryt.ble.BleGapScanCallBack
import org.inspir3.telemetryt.view.MainView

class MainActivity : ComponentActivity() {
    private lateinit var gap: Gap

    private val viewModel: AppViewModel = AppViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(I3.TAG, "MainActivity.onCreate()")
        super.onCreate(savedInstanceState)

        if (Init.permissionsIsMissing(this)) return
        if (Init.requirementsIsMissing(this)) return

        this.startBle()

        setContent {
            TelemetryTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(
                        viewModel = viewModel,
                        resetRoute = {
                            this.viewModel.setPressureMin(32767)
                            this.viewModel.setPressureMax(-32768)
                            this.viewModel.setTemperatureMin(32767)
                            this.viewModel.setTemperatureMax(-32768)
                        },
                        exitRoute = { this.stopApplication() },
                    )
                }
            }
        }
    }

    private fun startBle() {
        this.gap = Gap(
            context = this,
            gapScanCallback = BleGapScanCallBack(viewModel = viewModel),
        )
        this.gap.startScan("MX5")
    }

    /**
     * Stop the application
     */
    fun stopApplication() {
        Log.d(I3.TAG, "MainActivity.stopApplication()")

        this.gap.stopScan()
        Log.d(I3.TAG, "Call ComponentActivity.finish()")
        this.finish()
    }
}

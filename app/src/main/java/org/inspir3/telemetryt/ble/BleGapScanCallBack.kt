package org.inspir3.telemetryt.ble

import org.inspir3.common.Binary
import org.inspir3.common.ble.GapScanCallback
import org.inspir3.telemetryt.AppViewModel
import java.time.Instant

class BleGapScanCallBack(
    val viewModel: AppViewModel,
) : GapScanCallback() {
    override fun onData(services: Map<String, ByteArray>) {
        viewModel.setTs(Instant.now().epochSecond)

        val temperature = services["00001809-0000-1000-8000-00805f9b34fb"]?.let { Binary.byteArrayToShort(it) } ?: -32768 //Health Thermometer Service (Signed short)
        val pressure = services["00002a6d-0000-1000-8000-00805f9b34fb"]?.let { Binary.byteArrayToShort(it) } ?: -32768 //Pressure characteristic (Signed short)

        viewModel.setTemperatureActual(temperature)
        viewModel.setPressureActual(pressure)

        if (temperature < (viewModel.getTemperatureMin() ?: 32767)) viewModel.setTemperatureMin(temperature)
        if (pressure < (viewModel.getPressureMin() ?: 32767)) viewModel.setPressureMin(pressure)
        if (temperature > (viewModel.getTemperatureMax() ?: -32768)) viewModel.setTemperatureMax(temperature)
        if (pressure > (viewModel.getPressureMax() ?: -32768)) viewModel.setPressureMax(pressure)

        /*
        Common.temperature = this.setNewData(
            value = services["00001809-0000-1000-8000-00805f9b34fb"]?.let { Binary.byteArrayToShort(it) } ?: -32768, //Health Thermometer Service (Signed short)
            oldData = Common.temperature,
        )
        Common.pressure = this.setNewData(
            value = services["00002a6d-0000-1000-8000-00805f9b34fb"]?.let { Binary.byteArrayToShort(it) } ?: -32768, //Pressure characteristic (Signed short)
            oldData = Common.pressure,
        )*/

        //Log.d(I3.TAG, "temperature : ${Common.temperature.actual.value} pressure : ${Common.pressure.actual.value}")
    }

    /*
    private fun setNewData(value: Short, oldData: Data): Data {
        val ret = Data(
            min = oldData.min,
            max = oldData.max,
            actual = BleData(
                ts = Instant.now().epochSecond,
                value = value,
            ),
        )

        //Min value
        if (value < oldData.min.value) {
            ret.min.ts = Instant.now().epochSecond
            ret.min.value = value
        }

        //Max value
        if (value > oldData.max.value) {
            ret.max.ts = Instant.now().epochSecond
            ret.max.value = value
        }

        return ret
    }*/
}

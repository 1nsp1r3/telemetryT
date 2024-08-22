package org.inspir3.telemetryt

import org.inspir3.telemetryt.ble.BleData

class Data(
    val min: BleData = BleData(value = 32767),
    val max: BleData = BleData(value = -32768),
    val actual: BleData = BleData(),
)

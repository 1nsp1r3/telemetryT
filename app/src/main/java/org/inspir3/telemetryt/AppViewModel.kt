package org.inspir3.telemetryt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {
    private val _ts = MutableLiveData(0L)
    val ts: LiveData<Long> = _ts

    private val _temperatureMin = MutableLiveData(32767)
    private val _temperatureMax = MutableLiveData(-32768)
    private val _temperatureActual = MutableLiveData(0)
    val temperatureMin: LiveData<Int> = _temperatureMin
    val temperatureMax: LiveData<Int> = _temperatureMax
    val temperatureActual: LiveData<Int> = _temperatureActual

    private val _pressureMin = MutableLiveData(32767)
    private val _pressureMax = MutableLiveData(-32768)
    private val _pressureActual = MutableLiveData(0)
    val pressureMin: LiveData<Int> = _pressureMin
    val pressureMax: LiveData<Int> = _pressureMax
    val pressureActual: LiveData<Int> = _pressureActual

    fun setTs(value: Long){
        this._ts.value = value
    }
    fun setTemperatureActual(value: Short){
        this._temperatureActual.value = value.toInt()
    }
    fun setTemperatureMin(value: Short){
        this._temperatureMin.value = value.toInt()
    }
    fun getTemperatureMin(): Short? = this._temperatureMin.value?.toShort()
    fun setTemperatureMax(value: Short){
        this._temperatureMax.value = value.toInt()
    }
    fun getTemperatureMax(): Short? = this._temperatureMax.value?.toShort()
    fun setPressureActual(value: Short){
        this._pressureActual.value = value.toInt()
    }
    fun setPressureMin(value: Short){
        this._pressureMin.value = value.toInt()
    }
    fun getPressureMin(): Short? = this._pressureMin.value?.toShort()
    fun setPressureMax(value: Short){
        this._pressureMax.value = value.toInt()
    }
    fun getPressureMax(): Short? = this._pressureMax.value?.toShort()
}

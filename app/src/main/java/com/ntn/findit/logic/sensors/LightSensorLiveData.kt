package com.ntn.findit.logic.sensors

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData

class LightSensorLiveData(application: Application): LiveData<Float>() {
    private val sensorManager = application.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val lightSensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    private val lightSensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                value = it.values[0]
            }
        }
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
    }

    override fun onActive() {
        super.onActive()
        sensorManager.registerListener(lightSensorListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onInactive() {
        super.onInactive()
        sensorManager.unregisterListener(lightSensorListener)
    }
}
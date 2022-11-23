package com.ntn.findit.logic.sensors

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData

class CompassSensorLiveData(application: Application) : LiveData<Float>() {
    private val sensorManager =
        application.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    private val sensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null) {
                if (event.sensor == accelerometerSensor) {
                    System.arraycopy(event.values, 0, lastAccelerometer, 0, event.values.size)
                    isLastAccelerometerArrayCopied = true
                } else if (event.sensor == magnetometerSensor) {
                    System.arraycopy(event.values, 0, lastMagnetometer, 0, event.values.size)
                    isLastMagnetometerArrayCopied = true
                }
            }
            if (isLastMagnetometerArrayCopied &&
                isLastAccelerometerArrayCopied &&
                System.currentTimeMillis() - lastUpdatedTime > 250) {

                SensorManager.getRotationMatrix(rotationMatrix, null, lastAccelerometer, lastMagnetometer)
                SensorManager.getOrientation(rotationMatrix, orientation)

                val azimuthInRadians = orientation[0]
                val azimuthUnDegrees = Math.toDegrees(azimuthInRadians.toDouble())

                currentDegree = (-azimuthUnDegrees).toFloat()
                lastUpdatedTime = System.currentTimeMillis()

                value = azimuthUnDegrees.toFloat()
            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
    }


    val lastAccelerometer = FloatArray(3)
    val lastMagnetometer = FloatArray(3)
    val rotationMatrix = FloatArray(9)
    val orientation = FloatArray(3)

    var isLastAccelerometerArrayCopied = false
    var isLastMagnetometerArrayCopied = false

    var lastUpdatedTime = 0L
    var currentDegree = 0f


    override fun onActive() {
        super.onActive()
        sensorManager.registerListener(
            sensorListener,
            accelerometerSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        sensorManager.registerListener(
            sensorListener,
            magnetometerSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onInactive() {
        super.onInactive()
        sensorManager.unregisterListener(sensorListener, accelerometerSensor)
        sensorManager.unregisterListener(sensorListener, magnetometerSensor)
    }

}
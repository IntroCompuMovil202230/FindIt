package com.ntn.findit.ui.screen.magenetromene

import android.content.res.Resources
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ntn.findit.ui.theme.FindItTheme

class CompassActivity : ComponentActivity(){

    private val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    private val sensorMagenetromene = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    private var _x = MutableLiveData<String>()
    val x: LiveData<String> = _x

    private var _y = MutableLiveData<String>()
    val y: LiveData<String> = _y

    private var _z = MutableLiveData<String>()
    val z: LiveData<String> = _z

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CompassScreen(x,y,z)
                    MageneTromeneSensor()
                }
            }
        }
    }

    fun MageneTromeneSensor() {

        val proximitySensorEventListener = object : SensorEventListener {

            // on below line we are creating a sensor on sensor changed
            override fun onSensorChanged(event: SensorEvent) {
                // check if the sensor type is proximity sensor.
                if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                    Log.d("Mio", "Signed successfull")
                            _x.value = event.values[0].toString()
                    _y.value = event.values[1].toString()
                    _z.value = event.values[2].toString()
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                TODO("Not yet implemented")
            }
        }
    }

}



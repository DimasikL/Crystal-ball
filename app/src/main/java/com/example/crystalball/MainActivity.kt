package com.example.crystalball

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crystalball.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var sManager: SensorManager
    lateinit var binding: ActivityMainBinding
    lateinit var value: FloatArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tvSensor = binding.textView
        sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sListener = object : SensorEventListener {
            override fun onSensorChanged(sEvent: SensorEvent?) {
                value = sEvent?.values!!
                val sData = "X: ${value?.get(0)}\n Y: ${value?.get(1)}\n Z: ${value?.get(2)}"
                tvSensor.text = sData
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }

        }
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun getReto(): String {
        return resources.getStringArray(R.array.retos)[randomNumber()]
    }

    private fun randomNumber(): Int {
        val size = resources.getStringArray(R.array.retos).size - 1
        return (0..size).random()
    }
}
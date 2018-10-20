package com.gresikdev.gresikfuniot.kotlin

import android.content.Context
import android.graphics.Color
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gresikdev.gresikfuniot.R
import com.squareup.seismic.ShakeDetector
import kotlinx.android.synthetic.main.activity_step5.*
import org.jetbrains.anko.doAsync
import java.net.URL
import java.util.*

class Step5Activity : AppCompatActivity() {

    private lateinit var shakeDetector: ShakeDetector
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step5)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector = ShakeDetector {
            doChangeColor()
        }

        doChangeColor()
    }

    private fun doChangeColor() {
        val red = Random().nextInt(256)
        val green = Random().nextInt(256)
        val blue = Random().nextInt(256)

        etValRed?.setText(red.toString())
        etValGreen?.setText(green.toString())
        etValBlue?.setText(blue.toString())

        val color = Color.argb(255, red, green, blue)
        btnCheckColor.setBackgroundColor(color)

        doAsync {
            val urlArduino = "http://10.37.11.235/genericArgs?R=$red&G=$green&B=$blue"
            URL(urlArduino).readText()
        }
    }


    override fun onResume() {
        super.onResume()
        shakeDetector.start(sensorManager)
    }

    override fun onPause() {
        super.onPause()
        shakeDetector.stop()
    }
}
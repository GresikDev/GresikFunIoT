package com.gresikdev.gresikfuniot.kotlin

import android.content.Context
import android.graphics.Color
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.gresikdev.gresikfuniot.R
import com.squareup.seismic.ShakeDetector
import kotlinx.android.synthetic.main.activity_optional.*
import org.jetbrains.anko.doAsync
import java.net.URL
import java.util.*

class OptionalActivity : AppCompatActivity() {

    private lateinit var shakeDetector: ShakeDetector
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_optional)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector = ShakeDetector {
            doChangeColor()
        }

        btnCheckColor?.setOnClickListener {
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

        val url = etIPAdresss?.text?.toString()

        val urlArduino = "http://$url/genericArgs?R=$red&G=$green&B=$blue"

        doAsync {
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


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
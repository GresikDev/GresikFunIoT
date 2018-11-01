package com.gresikdev.gresikfuniot.kotlin

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.gresikdev.gresikfuniot.R
import kotlinx.android.synthetic.main.activity_step2.*
import org.jetbrains.anko.doAsync
import java.net.URL

class Step2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnCheckColor?.setOnClickListener {
            val red = etValRed?.text?.toString()?.toIntOrNull() ?: 0
            val green = etValGreen?.text?.toString()?.toIntOrNull() ?: 0
            val blue = etValBlue?.text?.toString()?.toIntOrNull() ?: 0

            val color = Color.argb(255, red, green, blue)
            it.setBackgroundColor(color)
        }

        btnSubmitColor?.setOnClickListener {
            val red = etValRed?.text?.toString()?.toIntOrNull() ?: 0
            val green = etValGreen?.text?.toString()?.toIntOrNull() ?: 0
            val blue = etValBlue?.text?.toString()?.toIntOrNull() ?: 0

            doAsync {
                val urlArduino = "http://10.37.11.235/genericArgs?R=$red&G=$green&B=$blue"
                URL(urlArduino).readText()
            }
        }
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
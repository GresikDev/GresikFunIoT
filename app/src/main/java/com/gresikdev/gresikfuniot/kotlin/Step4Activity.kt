package com.gresikdev.gresikfuniot.kotlin

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gresikdev.gresikfuniot.R
import kotlinx.android.synthetic.main.activity_step4.*
import org.jetbrains.anko.doAsync
import java.net.URL
import java.util.*

class Step4Activity : AppCompatActivity() {
    private var red: Int? = null
    private var green: Int? = null
    private var blue: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step4)

        btnCheckColor?.setOnClickListener {
            red = Random().nextInt(256)
            green = Random().nextInt(256)
            blue = Random().nextInt(256)

            etValRed?.setText(red.toString())
            etValGreen?.setText(green.toString())
            etValBlue?.setText(blue.toString())

            val color = Color.argb(255, red!!, green!!, blue!!)
            it.setBackgroundColor(color)
        }

        btnSubmitColor?.setOnClickListener {
            doAsync {
                val urlArduino = "http://10.37.11.235/genericArgs?R=${red ?: 0}&G=${green
                        ?: 0}&B=${blue ?: 0}"
                URL(urlArduino).readText()
            }
        }
    }
}
package com.gresikdev.gresikfuniot.kotlin

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.gresikdev.gresikfuniot.R
import kotlinx.android.synthetic.main.activity_step3.*
import org.jetbrains.anko.doAsync
import java.net.URL

class Step3Activity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step3)

        etValRed?.setText(seekBarRed?.progress.toString())
        etValGreen?.setText(seekBarGreen?.progress.toString())
        etValBlue?.setText(seekBarBlue?.progress.toString())
        checkColor()

        seekBarRed?.setOnSeekBarChangeListener(this)
        seekBarGreen?.setOnSeekBarChangeListener(this)
        seekBarBlue?.setOnSeekBarChangeListener(this)

        btnSubmitColor?.setOnClickListener {
            val red = seekBarRed?.progress ?: 0
            val green = seekBarGreen?.progress ?: 0
            val blue = seekBarBlue?.progress ?: 0

            doAsync {
                val urlArduino = "http://10.37.11.235/genericArgs?R=$red&G=$green&B=$blue"
                URL(urlArduino).readText()
            }
        }
    }

    private fun checkColor() {
        val red = seekBarRed?.progress ?: 0
        val green = seekBarGreen?.progress ?: 0
        val blue = seekBarBlue?.progress ?: 0

        val color = Color.argb(255, red, green, blue)
        btnCheckColor.setBackgroundColor(color)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (seekBar?.id == R.id.seekBarRed) {
            etValRed?.setText(progress.toString())
        } else if (seekBar?.id == R.id.seekBarGreen) {
            etValGreen?.setText(progress.toString())
        } else if (seekBar?.id == R.id.seekBarBlue) {
            etValBlue?.setText(progress.toString())
        }
        checkColor()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
}
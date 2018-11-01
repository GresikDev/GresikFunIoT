package com.gresikdev.gresikfuniot.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.gresikdev.gresikfuniot.R
import kotlinx.android.synthetic.main.activity_step1.*
import org.jetbrains.anko.doAsync
import java.net.URL

class Step1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step1)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnSubmitBlack?.setOnClickListener {
            doAsync {
                val urlArduino = "http://10.37.11.235/genericArgs?R=0&G=0&B=0"
                URL(urlArduino).readText()
            }
        }

        btnSubmitRed?.setOnClickListener {
            doAsync {
                val urlArduino = "http://10.37.11.235/genericArgs?R=255&G=0&B=0"
                URL(urlArduino).readText()
            }
        }

        btnSubmitGreen?.setOnClickListener {
            doAsync {
                val urlArduino = "http://10.37.11.235/genericArgs?R=0&G=255&B=0"
                URL(urlArduino).readText()
            }
        }

        btnSubmitBlue?.setOnClickListener {
            doAsync {
                val urlArduino = "http://10.37.11.235/genericArgs?R=0&G=0&B=255"
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
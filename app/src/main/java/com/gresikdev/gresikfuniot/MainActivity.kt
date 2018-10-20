package com.gresikdev.gresikfuniot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gresikdev.gresikfuniot.kotlin.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToStep1?.setOnClickListener {
            startActivity(Intent(this@MainActivity, Step1Activity::class.java))
        }
        btnToStep2?.setOnClickListener {
            startActivity(Intent(this@MainActivity, Step2Activity::class.java))
        }
        btnToStep3?.setOnClickListener {
            startActivity(Intent(this@MainActivity, Step3Activity::class.java))
        }
        btnToStep4?.setOnClickListener {
            startActivity(Intent(this@MainActivity, Step4Activity::class.java))
        }
        btnToStep5?.setOnClickListener {
            startActivity(Intent(this@MainActivity, Step1Activity::class.java))
        }
        btnToAdditional?.setOnClickListener {
            startActivity(Intent(this@MainActivity, Step1Activity::class.java))
        }
    }
}

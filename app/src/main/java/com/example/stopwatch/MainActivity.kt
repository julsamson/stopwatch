package com.example.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //find button/textview and giving them names
        var btnStart = findViewById<Button>(R.id.button)
        var btnStop = findViewById<Button>(R.id.button2)
        var btnReset = findViewById<Button>(R.id.button4)
        var btnResume = findViewById<Button>(R.id.button3)
        var chronometer = findViewById<Chronometer>(R.id.stop)
        var isRunning = false
        var stopAt: Long = 0

        //when clicked start button it should respond as follows
        btnStart.setOnClickListener {
            if (!isRunning) {
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
            }
            isRunning = true
        }

        //when clicked resume button it should respond as follows
        btnResume.setOnClickListener {
            if (!isRunning) {
                chronometer.base = SystemClock.elapsedRealtime() - stopAt
                chronometer.start()
            }
            isRunning = true
        }

        //when clicked stop button it should respond as follows
        btnStop.setOnClickListener {
            if (isRunning) {
                stopAt = SystemClock.elapsedRealtime() - chronometer.base
                chronometer.stop()
            }
            isRunning = false
        }

        //when clicked reset button it should respond as follows
        btnReset.setOnClickListener{
            if (isRunning){
                chronometer.stop()
            }
            chronometer.base = SystemClock.elapsedRealtime()
        }
    }
}
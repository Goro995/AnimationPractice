package com.trial.animationtraining

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import java.util.Timer
import java.util.TimerTask

class TimerActivity : Activity() {

    internal var time: Long = 0
    internal var timer: Timer? = null
    internal var task: TimerTask? = null
    internal var startTimer: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_layout)

        startTimer = findViewById(R.id.buttonStart) as Button

        startTimer!!.setOnClickListener {
            // TODO Auto-generated method stub
            if (startTimer!!.text.toString() == "START") {
                startTimer!!.text = "STOP"
                time = 0
                startTimer()

            } else {
                timer!!.cancel()
                timer!!.purge()
                val timerText = findViewById(R.id.timerText) as TextView
                timerText.text = "0 seconds"
                startTimer!!.text = "START"
            }
        }
    }

    fun startTimer() {
        timer = Timer()
        task = object : TimerTask() {

            override fun run() {
                runOnUiThread {
                    val timerText = findViewById(R.id.timerText) as TextView
                    timerText.text = time.toString() + " seconds"
                    if (time >= 0)
                        time += 1
                }
            }
        }
        timer!!.scheduleAtFixedRate(task, 0, 1000)
    }
}
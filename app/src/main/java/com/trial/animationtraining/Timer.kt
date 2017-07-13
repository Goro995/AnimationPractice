package com.trial.animationtraining

import android.app.Activity
import android.net.wifi.p2p.WifiP2pManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.TimerTask


class Timer : Activity() {

    internal var timerText: TextView? = null
    internal var startButton: Button? = null
    internal var stopButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_layout)

        timerText = findViewById(R.id.timerText) as TextView
        startButton = findViewById(R.id.buttonStart) as Button

        val timer = TimeCounter((120 * 1000).toLong(), 1000)

        startButton!!.setOnClickListener { timer.start() }

        stopButton!!.setOnClickListener { timer.cancel() }
    }

    inner class TimeCounter(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
        internal var countUpTimer: Int = 0

        init {
            countUpTimer = 0
        }

        override fun onTick(l: Long) {
            //since each tick interval is one second
            // just add 1 to this each time
            timerText!!.text = countUpTimer.toString() + " seconds"
            countUpTimer = countUpTimer + 1
        }

        override fun onFinish() {
            //reset counter to 0 if you want
            countUpTimer = 0
        }
    }
}

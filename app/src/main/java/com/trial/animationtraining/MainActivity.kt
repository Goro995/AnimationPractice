package com.trial.animationtraining

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast

import android.R.attr.animation

class MainActivity : Activity(), Animation.AnimationListener {

    internal var zoomIn: Animation? = null
    internal var zoomOut: Animation? = null

    internal var buttonOne: Button? = null
    internal var buttonTwo: Button? = null

    internal var i = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zoomIn = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_in)
        zoomOut = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_out)

        buttonOne = findViewById(R.id.buttonOne) as Button
        buttonTwo = findViewById(R.id.buttonTwo) as Button

        zoomIn!!.setAnimationListener(this)
        zoomOut!!.setAnimationListener(this)


        buttonOne!!.setOnClickListener {
            if (!i) {
                buttonTwo!!.startAnimation(zoomIn)
                i = true
            } else {
                buttonTwo!!.startAnimation(zoomOut)
                i = false
            }
        }
    }

    override fun onAnimationEnd(animation: Animation) {
        Log.d("TAG", "Animation Stopped!")
        if (animation === zoomOut) {
            buttonTwo!!.text = "Grow me"
        }
        if (animation === zoomIn) {
            buttonTwo!!.text = "Reduce me"
        }
    }

    override fun onAnimationRepeat(animation: Animation) {}

    override fun onAnimationStart(animation: Animation) {}
}

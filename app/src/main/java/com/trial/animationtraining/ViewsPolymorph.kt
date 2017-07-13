package com.trial.animationtraining

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView

import com.trial.animationtraining.R.anim.move_button_down
import com.trial.animationtraining.R.anim.middle_view_transformation
import com.trial.animationtraining.R.anim.top_view_transformation


class ViewsPolymorph : Activity(), Animation.AnimationListener {

    internal var bottomViewTransformation: Animation? = null
    internal var moveButtonDown: Animation? = null
    internal var topViewTransformation: Animation? = null
    internal var middleViewTransformation: Animation? = null

    internal var colorAnimation: ValueAnimator? = null

    internal var activationButton: Button? = null

//    internal var topView: TextView? = null
//    internal var middleView: TextView? = null
//    internal var bottomView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.polymorph_layout)

        bottomViewTransformation = AnimationUtils.loadAnimation(applicationContext, R.anim.bottom_view_transformation)
        moveButtonDown = AnimationUtils.loadAnimation(applicationContext, move_button_down)
        topViewTransformation = AnimationUtils.loadAnimation(applicationContext, top_view_transformation)
        middleViewTransformation = AnimationUtils.loadAnimation(applicationContext, middle_view_transformation)

        activationButton = findViewById(R.id.activationButton) as Button

        val topView = findViewById(R.id.topView) as TextView
        val middleView = findViewById(R.id.middleView) as TextView
        val bottomView = findViewById(R.id.bottomView) as TextView

        moveButtonDown!!.setAnimationListener(this)
        topViewTransformation!!.setAnimationListener(this)
        middleViewTransformation!!.setAnimationListener(this)
        bottomViewTransformation!!.setAnimationListener(this)

        val blueColor = ContextCompat.getColor(this, R.color.blue)
        val redColor = ContextCompat.getColor(this, R.color.red)
        val greenColor = ContextCompat.getColor(this, R.color.green)

        activationButton!!.setOnClickListener {
            activationButton!!.startAnimation(moveButtonDown)
            bottomView!!.startAnimation(topViewTransformation)
            middleView!!.startAnimation(middleViewTransformation)
            topView!!.startAnimation(bottomViewTransformation)
            colorTransition(redColor, greenColor, bottomView)
            colorTransition(greenColor, blueColor, middleView)
            colorTransition(blueColor, redColor, topView)
        }
    }

    override fun onAnimationEnd(animation: Animation) {
        Log.d("TAG", "Animation Stopped!")


    }

    override fun onAnimationRepeat(animation: Animation) {
        Log.d("TAG", "Animation Repeating!")
    }

    override fun onAnimationStart(animation: Animation) {
        Log.d("TAG", "Animation Started!")
    }

    private fun colorTransition(colorFrom: Int, colorTo: Int, view: View) {
        colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation!!.duration = 2000 // milliseconds
        colorAnimation!!.addUpdateListener { animator -> view.setBackgroundColor(animator.animatedValue as Int) }
        colorAnimation!!.start()
    }
}

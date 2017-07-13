package com.trial.animationtraining;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import static com.trial.animationtraining.R.anim.move_button_down;
import static com.trial.animationtraining.R.anim.middle_view_transformation;
import static com.trial.animationtraining.R.anim.top_view_transformation;


public class ViewsPolymorph extends Activity implements Animation.AnimationListener {

    Animation transition;
    Animation bottomViewTransformation;
    Animation resizeMiddleView;
    Animation fade;
    Animation moveButtonDown;
    Animation topViewTransformation;
    Animation middleViewTransformation;

    ValueAnimator colorAnimation;

    Button activationButton;

    TextView topView;
    TextView middleView;
    TextView bottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polymorph_layout);

        bottomViewTransformation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_view_transformation);
        moveButtonDown = AnimationUtils.loadAnimation(getApplicationContext(), move_button_down);
        topViewTransformation = AnimationUtils.loadAnimation(getApplicationContext(), top_view_transformation);
        middleViewTransformation = AnimationUtils.loadAnimation(getApplicationContext(), middle_view_transformation);

        activationButton = (Button) findViewById(R.id.activationButton);

        topView = (TextView) findViewById(R.id.topView);
        middleView = (TextView) findViewById(R.id.middleView);
        bottomView = (TextView) findViewById(R.id.bottomView);

        moveButtonDown.setAnimationListener(this);
        topViewTransformation.setAnimationListener(this);
        middleViewTransformation.setAnimationListener(this);
        bottomViewTransformation.setAnimationListener(this);

        final int blueColor = ContextCompat.getColor(this, R.color.blue);
        final int redColor = ContextCompat.getColor(this, R.color.red);
        final int greenColor = ContextCompat.getColor(this, R.color.green);

        activationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                activationButton.startAnimation(moveButtonDown);
                bottomView.startAnimation(topViewTransformation);
                middleView.startAnimation(middleViewTransformation);
                topView.startAnimation(bottomViewTransformation);
                colorTransition(redColor, greenColor, bottomView);
                colorTransition(greenColor, blueColor, middleView);
                colorTransition(blueColor, redColor, topView);
            }
        });
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.d("TAG", "Animation Stopped!");


    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.d("TAG", "Animation Repeating!");
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Log.d("TAG", "Animation Started!");
    }

    private void colorTransition(int colorFrom, int colorTo, final View view) {
        colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(2000); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }
}

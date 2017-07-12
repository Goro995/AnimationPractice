package com.trial.animationtraining;

import android.app.Activity;
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

        activationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                activationButton.startAnimation(moveButtonDown);
                bottomView.startAnimation(topViewTransformation);
                middleView.startAnimation(middleViewTransformation);
                topView.startAnimation(bottomViewTransformation);

            }
        });
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.d("TAG", "Animation Stopped!");
        topView.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        middleView.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        bottomView.setBackgroundColor(ContextCompat.getColor(this, R.color.green));

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.d("TAG", "Animation Repeating!");
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Log.d("TAG", "Animation Started!");
    }
}

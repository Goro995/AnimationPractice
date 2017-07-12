package com.trial.animationtraining;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.animation;

public class MainActivity extends Activity implements Animation.AnimationListener {

    Animation zoomIn;
    Animation zoomOut;

    Button buttonOne;
    Button buttonTwo;

    boolean i = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        zoomOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);

        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);

        zoomIn.setAnimationListener(this);
        zoomOut.setAnimationListener(this);


        buttonOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!i) {
                    buttonTwo.startAnimation(zoomIn);
                    buttonTwo.setText("Reduce me");
                    i = true;
                } else {
                    buttonTwo.startAnimation(zoomOut);
                    buttonTwo.setText("Grow me");
                    i = false;
                }
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
}

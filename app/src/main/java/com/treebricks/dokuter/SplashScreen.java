package com.treebricks.dokuter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.treebricks.dokuter.utils.SharedPrefManager;

public class SplashScreen extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        ImageView imageViewChef = findViewById(R.id.imageView_logo);
        Animation loadAnimation = AnimationUtils.loadAnimation(this,R.anim.alpha_transaction);
        imageViewChef.startAnimation(loadAnimation);

        TranslateAnimation animationchef = new TranslateAnimation(-700.0f, 0.0f,
                0.0f, 0.0f);    //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animationchef.setDuration(1000);     // animation duration
        animationchef.setRepeatCount(0);    // animation repeat count
        animationchef.setRepeatMode(2);      // repeat animation (left to right, right to left )
        animationchef.setFillAfter(true);
        imageViewChef.startAnimation(animationchef);  // start animation

        sharedPrefManager = new SharedPrefManager(SplashScreen.this);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                    Intent intent;
                    if(sharedPrefManager.isLoggedIn()) {
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                    }else{
                        intent = new Intent(getApplicationContext(),WelcomeActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}

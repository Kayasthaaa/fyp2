package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.fyp.AppLogin.MainActivity;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4995;

    ImageView logo,splashImg;
    LottieAnimationView lottieAnimationView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo);
        splashImg = findViewById(R.id.img);
        textView = findViewById(R.id.Motto);
        lottieAnimationView = findViewById(R.id.lottie);

        splashImg.animate().translationY(-2800).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1850).setDuration(1000).setStartDelay(4000);
        textView.animate().translationY(1800).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1800).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class );
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);


    }
}
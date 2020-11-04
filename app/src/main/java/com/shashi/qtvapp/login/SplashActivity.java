package com.shashi.qtvapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.shashi.qtvapp.MainActivity;
import com.shashi.qtvapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 2400);

        TextView textView = findViewById(R.id.textView_splash_activity);
        ImageView imageView = findViewById(R.id.imageViewSplash);

        Animation animationImageView = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_imageview);
        Animation animationTextView = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_textview);

        imageView.setAnimation(animationImageView);
        textView.setAnimation(animationTextView);

    }

}
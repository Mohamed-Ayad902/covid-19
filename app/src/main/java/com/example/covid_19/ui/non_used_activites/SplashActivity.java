package com.example.covid_19.ui.non_used_activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.covid_19.R;
import com.example.covid_19.ui.used_activities.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    protected static final String MY_PREFS_NAME = "shared_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, OnboardingActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                checkIfShowed();
            }
        }, 4200);

    }


    private void checkIfShowed() {

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean("boarding", true);
        editor.apply();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean isShowed = prefs.getBoolean("boarding", false);

        if (isShowed)
            startActivity(new Intent(getBaseContext(), HomeActivity.class));

        finish();

    }


}
package com.example.covid_19.ui.non_used_activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.covid_19.adapters.OnboardingAdapter;
import com.example.covid_19.R;
import com.example.covid_19.ui.used_activities.HomeActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private MaterialButton button;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        linearLayout = findViewById(R.id.main_linearLayout);
        viewPager = findViewById(R.id.viewPager);
        button = findViewById(R.id.main_btn_nextPage);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        showOrNo();

        List<Fragment> pages = new ArrayList<>();
        pages.add(new PageCoronaNews());
        pages.add(new PageAdvice());
        pages.add(new PageNoCorona());


        OnboardingAdapter adapter = new OnboardingAdapter(getSupportFragmentManager(), pages);
        viewPager.setAdapter(adapter);

        setupIndicator();
        setCurrentIndicator(0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() + 1 < 3)
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                else {
                    startActivity(new Intent(getBaseContext(), HomeActivity.class));
                    finish();
                }
            }
        });

    }

    private void setupIndicator() {
        ImageView[] indicators = new ImageView[3];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(5, 0, 5, 0);
        for (int i = 0; i < 3; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indecator_imactive));
            indicators[i].setLayoutParams(layoutParams);
            linearLayout.addView(indicators[i]);
        }

    }

    private void setCurrentIndicator(int index) {
        int childCount = 3;
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) linearLayout.getChildAt(i);
            if (i == index)
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indecator_active));
            else
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indecator_imactive));
        }
        if (index + 1 == 3)
            button.setText(R.string.button_finish);
        else
            button.setText(R.string.button_next);
    }

    private void showOrNo() {

        SharedPreferences prefs = getSharedPreferences(SplashActivity.MY_PREFS_NAME, MODE_PRIVATE);
        boolean isShowed = prefs.getBoolean("boarding", false);

        if (isShowed)
            finish();


    }


}
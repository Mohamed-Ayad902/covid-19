package com.example.covid_19.ui.used_activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.covid_19.R;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class HomeActivity extends AppCompatActivity {

    private Fragment selectedFragment;
    private ChipNavigationBar navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         navigationBar= findViewById(R.id.main_nav_button);


        navigationBar.setItemSelected(R.id.menu_global, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, new GlobalFragment()).commit();


        navigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                switch (i) {
                    case R.id.menu_global:
                        selectedFragment = new GlobalFragment();
                        break;
                    case R.id.menu_local:
                        selectedFragment = new LocalFragment();
                        break;
                    case R.id.menu_question:
                        selectedFragment = new QuestionFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, selectedFragment).commit();
            }
        });


    }


}
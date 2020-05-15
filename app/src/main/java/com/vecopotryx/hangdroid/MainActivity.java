package com.vecopotryx.hangdroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Hangdroid");


        if(Model._wantExit){
            handleExit();
        }

        Button randomGameButton = (Button)findViewById(R.id.randomGameButton);
        randomGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
                GameControl.clearVariables();
            }
        });

        Button customGameButton = (Button)findViewById(R.id.customGameButton);
        customGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPickModeActivity();
            }
        });

        Button leaderboardButton = (Button)findViewById(R.id.leaderboardButton);
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do stuff
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                finish();
                startActivity(getIntent());
            }
        });

        Button exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleExit();
            }
        });



        SharedPreferences appSettingsPrefs = getSharedPreferences("AppSettingsPrefs", 0);
        final SharedPreferences.Editor sharedPrefsEditior = appSettingsPrefs.edit();


        final Switch darkModeSwitch = (Switch)findViewById((R.id.darkModeSwitch));

        CheckBox toggleAutoMode = (CheckBox)findViewById(R.id.toggleAutoMode);
        toggleAutoMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    sharedPrefsEditior.putBoolean("AUTO_MODE",true);
                    darkModeSwitch.setClickable(false);
                } else {
                    sharedPrefsEditior.putBoolean("AUTO_MODE",false);
                    darkModeSwitch.setClickable(true);
                }

            }
        });

        sharedPrefsEditior.putBoolean("DARK_MODE",false);

        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPrefsEditior.putBoolean("DARK_MODE",true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    finish();
                    startActivity(getIntent());
                } else {
                    sharedPrefsEditior.putBoolean("DARK_MODE",false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        if(appSettingsPrefs.getBoolean("AUTO_MODE", true)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            toggleAutoMode.setChecked(true);
            darkModeSwitch.setClickable(false);
        } else {
            toggleAutoMode.setChecked(false);
            darkModeSwitch.setClickable(true);
            if(appSettingsPrefs.getBoolean("DARK_MODE",false)){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }


    }

    private void refreshScreen(){
        finish();
        startActivity(getIntent());
    }

    public void openGameActivity(){
        Model._customMinLength = 1;
        Model._customMaxLength = 12;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void openPickModeActivity(){
        Intent intent = new Intent(this, PickModeActivity.class);
        startActivity(intent);
    }

    public void handleExit(){
        MainActivity.this.finish();
        System.exit(0);
    }
}

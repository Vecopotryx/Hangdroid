package com.vecopotryx.hangdroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
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


        Button lightModeButton = (Button)findViewById(R.id.lightModeButton);
        lightModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                sharedPrefsEditior.putBoolean("AUTO_MODE",false);
                sharedPrefsEditior.putBoolean("DARK_MODE",false);
                clearModeButtons();
                refreshScreen();
            }
        });

        Button darkModeButton = (Button)findViewById(R.id.darkModeButton);
        darkModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                sharedPrefsEditior.putBoolean("AUTO_MODE",false);
                sharedPrefsEditior.putBoolean("DARK_MODE",true);
                clearModeButtons();
                refreshScreen();
            }
        });


        Button autoModeButton = (Button)findViewById(R.id.autoModeButton);
        autoModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                sharedPrefsEditior.putBoolean("AUTO_MODE",true);
                clearModeButtons();
                refreshScreen();
            }
        });




        sharedPrefsEditior.putBoolean("DARK_MODE",false);

        if(appSettingsPrefs.getBoolean("AUTO_MODE",true)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
            clearModeButtons();
            autoModeButton.setBackground(getDrawable(getResources().getIdentifier("custom_button_selected", "drawable", getPackageName())));
        } else if(appSettingsPrefs.getBoolean("DARK_MODE", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            clearModeButtons();
            lightModeButton.setBackground(getDrawable(getResources().getIdentifier("custom_button_selected", "drawable", getPackageName())));
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            clearModeButtons();
            darkModeButton.setBackground(getDrawable(getResources().getIdentifier("custom_button_selected", "drawable", getPackageName())));
        }

    }

    private void clearModeButtons(){
        Button darkModeButton = (Button)findViewById(R.id.darkModeButton);
        Button lightModeButton = (Button)findViewById(R.id.lightModeButton);
        Button autoModeButton = (Button)findViewById(R.id.autoModeButton);

        darkModeButton.setBackground(getDrawable(getResources().getIdentifier("custom_button_selected", "drawable", getPackageName())));
        lightModeButton.setBackground(getDrawable(getResources().getIdentifier("custom_button_selected", "drawable", getPackageName())));
        autoModeButton.setBackground(getDrawable(getResources().getIdentifier("custom_button_selected", "drawable", getPackageName())));

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

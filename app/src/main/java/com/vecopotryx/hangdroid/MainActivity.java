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



        Button exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleExit();
            }
        });



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

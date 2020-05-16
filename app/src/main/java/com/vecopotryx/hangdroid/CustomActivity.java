package com.vecopotryx.hangdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        getSupportActionBar().setTitle("Custom settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SeekBar minLengthBar = (SeekBar)findViewById(R.id.minLengthBar);
        final SeekBar maxLengthBar = (SeekBar)findViewById(R.id.maxLengthBar);

        final TextView maxLengthText = (TextView)findViewById(R.id.maxLengthText);
        final TextView minLengthText = (TextView)findViewById(R.id.minLengthText);

        minLengthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minLengthText.setText("Minimum answer length: " + (progress + 1));
                maxLengthText.setText("Maximum answer length: " + (maxLengthBar.getProgress() + progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maxLengthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxLengthText.setText("Maximum answer length: " + (progress + minLengthBar.getProgress() + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void startGamePress(View v){
        GameControl.clearVariables();

        SeekBar minLengthBar = (SeekBar)findViewById(R.id.minLengthBar);
        Model._customMinLength = minLengthBar.getProgress() + 1;

        SeekBar maxLengthBar = (SeekBar)findViewById(R.id.maxLengthBar);
        Model._customMaxLength = maxLengthBar.getProgress() + 1;




        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }
}

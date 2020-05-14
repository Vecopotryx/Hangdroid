package com.vecopotryx.hangdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PickModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_mode);
        getSupportActionBar().setTitle("Custom game");

        Button button = (Button)findViewById(R.id.customButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomActivity();
            }
        });
    }

    public void openCustomActivity(){
        Intent intent = new Intent(this, CustomActivity.class);
        startActivity(intent);
    }

}

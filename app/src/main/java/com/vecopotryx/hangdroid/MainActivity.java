package com.vecopotryx.hangdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameControl.start();
    }

    String input;
    public void method(View v){
        // Button mButton;
        EditText mEdit;
        TextView mText;

        mEdit   = (EditText)findViewById(R.id.editText);
        Model.set_answer(mEdit.getText().toString());

        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(Model.get_answer());
    }
}

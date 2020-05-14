package com.vecopotryx.hangdroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        Model.set_answer("test");

        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(Model.get_answer());

        gameScreen(mEdit.getText().toString());
    }

    /**
     * This method handles the main logic for the screen that the user sees during the game.
     * It gets user input and compares it against a few conditions in order to act correctly.
     */
    public void gameScreen(String stringIn){
        if(stringIn.length() > 1){
            Toast.makeText(MainActivity.this,"Please enter a valid char", Toast.LENGTH_LONG).show();

        } else {
            /*
            if(!stringIn.equals("")){
                if(Model._charGuess.contains((stringIn.toCharArray()[0]))){
                    Model._infoForUser = "You've already guessed that";
                    gameScreen();
                } else if(!(Model._answerArray.contains((stringIn.toCharArray()[0])))) {
                    Model._charGuess.add(stringIn.toCharArray()[0]);
                    Model._wrongGuessesAmount += 1;
                    Model._infoForUser = "";
                } else {
                    Model._charGuess.add(stringIn.toCharArray()[0]);
                    Model._infoForUser = "";
                }
            }
             */
        }
    }
}

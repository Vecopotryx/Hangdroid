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
        Model.set_answer("test");
        populateArray();
    }

    String input;
    public void buttonPress(View v){
        // Button mButton;
        EditText mEdit;
        TextView mText;

        mEdit   = (EditText)findViewById(R.id.editText);
        Model.set_answer("test");

        gameScreen(mEdit.getText().toString().toLowerCase());

        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(Model._charGuess.toString());

        Toast.makeText(MainActivity.this,Model._answerArray.toString(), Toast.LENGTH_LONG).show();

    }

    /**
     * This method handles the main logic for the screen that the user sees during the game.
     * It gets user input and compares it against a few conditions in order to act correctly.
     */
    public void gameScreen(String stringIn){
        if(stringIn.length() > 1){
            Toast.makeText(MainActivity.this,"Please enter a valid char", Toast.LENGTH_LONG).show();
        } else if(Model._charGuess.contains(stringIn.toCharArray()[0])){
            Toast.makeText(MainActivity.this, "You've already guessed that", Toast.LENGTH_LONG).show();
        } else if(!(Model._answerArray.contains((stringIn.toCharArray()[0])))) {
            Toast.makeText(MainActivity.this, "Incorrect guess", Toast.LENGTH_LONG).show();
            Model._charGuess.add(stringIn.toCharArray()[0]);

            /*
            if(!stringIn.equals("")){
                if(Model._charGuess.contains((stringIn.toCharArray()[0]))){
                    Toast.makeText(MainActivity.this,"You've already guessed that", Toast.LENGTH_LONG).show();
                } else if(!(Model._answerArray.contains((stringIn.toCharArray()[0])))) {
                    Model._charGuess.add(stringIn.toCharArray()[0]);
                    Model._wrongGuessesAmount += 1;
                } else {
                    Model._charGuess.add(stringIn.toCharArray()[0]);
                }
            } else {
                Toast.makeText(MainActivity.this,"Please enter a valid char", Toast.LENGTH_LONG).show();
            }
             */
        }
    }

    /**
     * Populates the _answerArray with the contents of the _answer string. Useful in order to simplify comparing guesses with the answer.
     */
    public void populateArray(){
        for(char c : Model.get_answer().toCharArray()) {
            Model._answerArray.add(c);
        }
    }
}

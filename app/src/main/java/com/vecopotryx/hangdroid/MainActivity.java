package com.vecopotryx.hangdroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Model.set_answer("error");
        randomFromFile();
        populateArray();
        EditText inputBox   = (EditText)findViewById(R.id.editText);
        final Button button = (Button)findViewById((R.id.button));
        inputBox.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    button.performClick();
                    return true;
                }
                return false;
            }
        });
    }


    /**
     * Gets a random line from file and then stores it as the answer.
     */
    public void randomFromFile(){
        AssetManager assetManager = getAssets();
        InputStream is = null;


        String data = "java";
        Random random = new Random();
        try {
            is = assetManager.open("wordlist.txt");

        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "An error occured.", Toast.LENGTH_LONG).show();
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        for(int i = 0; i < random.nextInt(1000); i++){
            try {
                data = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } if(Model._customMinLength <= data.length() && data.length() <= Model._customMaxLength){
            Model.set_answer(data);
        } else {
            randomFromFile();
        }
    }

    String input;
    public void buttonPress(View v){

        EditText inputBox   = (EditText)findViewById(R.id.editText);



        gameScreen(inputBox.getText().toString().toLowerCase());

        TextView charGuessView = (TextView)findViewById(R.id.charGuessView);
        charGuessView.setText(Model._charGuess.toString());

        GameControl.updateDisplayWord();

        TextView displayWordView = (TextView)findViewById(R.id.displayWordView);
        displayWordView.setText(Model._displayWord);

        TextView wrongGuessesView = (TextView)findViewById(R.id.wrongGuessesView);
        wrongGuessesView.setText(Model._wrongGuessesAmount.toString());

        if(Model._displayWord.equals(Model.get_answer())){
            Toast.makeText(MainActivity.this, "You won", Toast.LENGTH_LONG).show();
        } else if(Model._wrongGuessesAmount > 6){
            Toast.makeText(MainActivity.this, "You lost", Toast.LENGTH_LONG).show();
        }

        inputBox.setText("");

        // Toast.makeText(MainActivity.this,Model._answerArray.toString(), Toast.LENGTH_LONG).show();

    }

    /**
     * This method handles the main logic for the screen that the user sees during the game.
     * It gets user input and compares it against a few conditions in order to act correctly.
     */
    public void gameScreen(String stringIn){
        if(stringIn.length() > 1 || stringIn.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter a valid char", Toast.LENGTH_LONG).show();
        } else if(Model._charGuess.contains(stringIn.toCharArray()[0])){
            Toast.makeText(MainActivity.this, "You've already guessed that", Toast.LENGTH_LONG).show();
        } else if(!(Model._answerArray.contains((stringIn.toCharArray()[0])))) {
            Toast.makeText(MainActivity.this, "Incorrect guess", Toast.LENGTH_LONG).show();
            Model._charGuess.add(stringIn.toCharArray()[0]);
            Model._wrongGuessesAmount += 1;
        } else {
            Model._charGuess.add(stringIn.toCharArray()[0]);
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

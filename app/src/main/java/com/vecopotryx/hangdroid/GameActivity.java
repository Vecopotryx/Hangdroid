package com.vecopotryx.hangdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setTitle("Hangdroid");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(!Model._gameRunning){
            Model.set_answer("error");
            randomFromFile();
            GameControl.populateArray();
            Model._gameRunning = true;
        }

        // Toast.makeText(GameActivity.this, "Just for debug: " + Model.get_answer(), Toast.LENGTH_SHORT).show();

        GameControl.updateDisplayWord();
        TextView displayWordView = (TextView)findViewById(R.id.displayWordView);
        displayWordView.setText(Model._displayWord);

        ImageView hangmanGraphic = (ImageView)findViewById(R.id.hangmanGraphic);

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                hangmanGraphic.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                break;
        }

        EditText inputBox   = (EditText)findViewById(R.id.editText);

        final Button button = (Button)findViewById((R.id.guessButton));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }

    private long backPressedTime;

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            Model._gameRunning = false;
            return;
        } else {
            Toast.makeText(getBaseContext(), "Press back again to leave current game", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    /**
     * Gets a random line from file and then stores it as the answer.
     */
    public void randomFromFile(){
        AssetManager assetManager = getAssets();
        InputStream is = null;


        String data = "android";
        Random random = new Random();
        try {
            is = assetManager.open("wordlist.txt");

        } catch (IOException e) {
            Toast.makeText(GameActivity.this, "An error occured.", Toast.LENGTH_LONG).show();
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

    public void guessButtonPress(View v){
        EditText inputBox   = (EditText)findViewById(R.id.editText);

        gameScreen(inputBox.getText().toString().toLowerCase());
        if(Model._charGuess.size() > 0){
            TextView charGuessView = (TextView)findViewById(R.id.charGuessView);
            charGuessView.setText(Model._charGuess.toString());
        }

        GameControl.updateDisplayWord();

        TextView displayWordView = (TextView)findViewById(R.id.displayWordView);
        displayWordView.setText(Model._displayWord);

        TextView wrongGuessesView = (TextView)findViewById(R.id.wrongGuessesView);
        wrongGuessesView.setText("Incorrect guesses: " + Model._wrongGuessesAmount.toString());

        if(Model._displayWord.equals(Model.get_answer().toLowerCase())){
            // Victory
            handleVictory();
        } else if(Model._wrongGuessesAmount >= 6){
            // Loss
            handleLoss();
        } else {
            ImageView hangmanGraphic = (ImageView)findViewById(R.id.hangmanGraphic);
            Drawable currentImage = getDrawable(getResources().getIdentifier("img_hangman" + Model._wrongGuessesAmount, "drawable", getPackageName()));
            hangmanGraphic.setImageDrawable(currentImage);
        }

        inputBox.setText("");
    }
    /**
     * Color matrix that flips the components (<code>-1.0f * c + 255 = 255 - c</code>)
     * and keeps the alpha intact.
     * Found on the interwebs
     */
    private static final float[] NEGATIVE = {
            -1.0f, 0, 0, 0, 255, // red
            0, -1.0f, 0, 0, 255, // green
            0, 0, -1.0f, 0, 255, // blue
            0, 0, 0, 1.0f, 0 // alpha
    };

    /**
     * This method handles the main logic for the screen that the user sees during the game.
     * It gets user input and compares it against a few conditions in order to act correctly.
     */
    public void gameScreen(String stringIn){
        if(stringIn.length() > 1 || stringIn.equals("")) {
            Toast.makeText(GameActivity.this, "Please enter a valid char", Toast.LENGTH_SHORT).show();
        } else if(Model._charGuess.contains(stringIn.toCharArray()[0])){
            Toast.makeText(GameActivity.this, "You've already guessed that", Toast.LENGTH_SHORT).show();
        } else if(!(Model._answerArray.contains((stringIn.toCharArray()[0])))) {
            Toast.makeText(GameActivity.this, "Incorrect guess", Toast.LENGTH_SHORT).show();
            Model._charGuess.add(stringIn.toCharArray()[0]);
            Model._wrongGuessesAmount += 1;
        } else {
            Model._charGuess.add(stringIn.toCharArray()[0]);
        }
    }

    private void handleVictory(){
        Model._gameRunning = false;
        Intent intent = new Intent(this, VictoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void handleLoss(){
        Model._gameRunning = false;
        Intent intent = new Intent(this, LossActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

package com.vecopotryx.hangdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VictoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        TextView answerView = (TextView)findViewById(R.id.victoryAnswerView);
        answerView.setText("The answer was: " + Model.get_answer());

        TextView guessAmountView = (TextView)findViewById(R.id.victoryGuessAmountView);
        guessAmountView.setText("Amount of guesses: " + Model._charGuess.size());

        TextView incorrectGuessView = (TextView)findViewById(R.id.victoryIncorrectGuessesView);
        incorrectGuessView.setText("Amount of incorrect guesses: " + Model._wrongGuessesAmount);

        TextView charGuessView = (TextView)findViewById(R.id.victoryCharGuessView);
        charGuessView.setText(Model._charGuess.toString());

        Button playAgainButton = (Button)findViewById(R.id.victoryPlayAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMenu();
            }
        });


        Button exitButton = (Button)findViewById(R.id.victoryExitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleExit();
            }
        });
    }

    public void backToMenu(){
        GameControl.clearVariables();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void handleExit(){
        Model._wantExit = true;
        backToMenu();
    }

    @Override
    public void onBackPressed() {
        backToMenu();
    }
}

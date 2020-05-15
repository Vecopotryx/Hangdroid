package com.vecopotryx.hangdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LossActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loss);

        TextView answerView = (TextView)findViewById(R.id.lossAnswerView);
        answerView.setText("The answer was: " + Model.get_answer());

        TextView guessView = (TextView)findViewById(R.id.lossGuessView);
        guessView.setText("Your guess was: " + Model._displayWord);

        TextView guessAmountView = (TextView)findViewById(R.id.lossGuessAmountView);
        guessAmountView.setText("Amount of guesses: " + Model._charGuess.size());

        TextView incorrectGuessView = (TextView)findViewById(R.id.lossIncorrectGuessesView);
        incorrectGuessView.setText("Amount of incorrect guesses: " + Model._wrongGuessesAmount);

        TextView charGuessView = (TextView)findViewById(R.id.lossCharGuessView);
        charGuessView.setText(Model._charGuess.toString());

        Button playAgainButton = (Button)findViewById(R.id.lossPlayAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMenu();
            }
        });

        Button exitButton = (Button)findViewById(R.id.lossExitButton);
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

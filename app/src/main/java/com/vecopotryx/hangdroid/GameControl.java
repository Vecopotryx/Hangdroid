package com.vecopotryx.hangdroid;

public class GameControl {
    public static void start(){
        Model.set_answer("test");
    }

    /**
     * This method contains logic for updating the word which contains the letters the user have guessed correctly and blank spaces for those the user haven't guessed yet.
     * Results in something looking like "han _ _ _ an"
     */
    public static void updateDisplayWord(){
        Model._displayWord = "";
        for(int i = 0; i < Model._answerArray.size(); i++){
            boolean isYes = false;
            for(int c = 0; c < Model._charGuess.size(); c++)
                if(Model._answerArray.get(i) == Model._charGuess.get(c)){
                    Model._displayWord += Model._charGuess.get(c) + "";
                    isYes = true;
                }
            if(!isYes){
                Model._displayWord += " _ ";
            }

        }
    }
}

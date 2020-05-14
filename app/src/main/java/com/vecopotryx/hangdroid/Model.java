package com.vecopotryx.hangdroid;

import java.util.ArrayList;

public class Model {
    public static Integer _wrongGuessesAmount;
    /**
     * Holds the answer
     */
    private static String _answer;

    /**
     * Lets other classes get the value from _answer
     * @return Returns the answer
     */
    public static String get_answer() {
        return _answer;
    }

    /**
     * Lets other classes set the value of _answer
     * @param answerIn The value that _answer will be set to
     */
    public static void set_answer(String answerIn) {
        _answer = answerIn;
    }

    public static ArrayList<Character> _answerArray = new ArrayList<Character>();

    /**
     * Holds previous guesses
     */
    public static ArrayList<Character> _charGuess = new ArrayList<Character>();

    /**
     * Holds the _displayWord. Which is the String that is displayed in the game in order to show what characters have been correct and how many more there are.
     */
    public static String _displayWord = "";
}

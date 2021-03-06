package com.vecopotryx.hangdroid;

import java.util.ArrayList;

public class Model {
    public static Integer _wrongGuessesAmount = 0;
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

    /**
     * Holds the minimum length that the answer can be. Can be modified by a user using the custom game mode.
     */
    public static Integer _customMinLength = 1;

    /**
     * Holds the maximum length that the answer can be. Can be modified by a user using the custom game mode.
     */
    public static Integer _customMaxLength = 12;

    /**
     * Used in order to be able to close app on exit click several activities in.
     */
    public static Boolean _wantExit = false;

    public static Boolean _gameRunning = false;
}

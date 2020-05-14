package com.vecopotryx.hangdroid;

public class Model {
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
}

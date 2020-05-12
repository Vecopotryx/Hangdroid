import java.util.ArrayList;

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


    /**
     * Holds previous guesses
     */
    public static ArrayList<Character> _charGuess = new ArrayList<Character>();;

    /**
     * Holds the _displayWord. Which is the String that is displayed in the game in order to show what characters have been correct and how many more there are.
     */
    public static String _displayWord = "";

    /**
     * Holds an ArrayList of the characters that make up the answer. (Has to be populated using GameControl.populateArray() first)
     */
    public static ArrayList<Character> _answerArray = new ArrayList<Character>();

    /**
     * Holds a String reserved for hints to the user. Useful for the Interface class to print hints as part of the game UI.
     */
    public static String _infoForUser = "";

    /**
     * Used to keep track of incorrect guesses
     */
    public static Integer _wrongGuessesAmount = 0;

    /**
     * Holds the minimum length that the answer can be. Can be modified by a user using the custom game mode.
     */
    public static Integer _customMinLength = 1;

    /**
     * Holds the maximum length that the answer can be. Can be modified by a user using the custom game mode.
     */
    public static Integer _customMaxLength = 12;

}

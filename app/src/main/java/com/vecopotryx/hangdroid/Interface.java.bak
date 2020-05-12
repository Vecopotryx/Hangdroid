import java.util.ArrayList;

public class Interface {
    /**
     * Prints the greeting menu (the first menu that the user is greeted with) with several options.
     */
    public static void printMenu(){
        printLogo();
        System.out.println("Please press one of the following keys to select");
        System.out.println("1. Random game");
        System.out.println("2. Custom game");
        System.out.println("3. Leaderboard");
        System.out.println("4. Exit");
    }

    /**
     * Prints the Hangman logo
     */
    public static void printLogo(){
        System.out.printf(" _______                                         \n" +
                "|   |   |.---.-.-----.-----.--------.---.-.-----.\n" +
                "|       ||  _  |     |  _  |        |  _  |     |\n" +
                "|___|___||___._|__|__|___  |__|__|__|___._|__|__|\n");
    }

    /**
     * Prints a menu with options for customizing the game.
     */
    public static void printCustomSettings(){
        System.out.println("Please press one of the following keys to select");
        System.out.println("1. Easy (1-3 characters)");
        System.out.println("2. Medium (3-5 characters");
        System.out.println("3. Difficult (5-8 characters)");
        System.out.println("4. Custom");
    }

    /**
     * Prints text asking for the maximum/minimum length that the answer can be.
     * @param type Contains a string with either maximum/minimum
     */
    public static void printPickCustomSpan(String type){
        System.out.print("Please enter the " + type +  " length: " );
    }

    /**
     * Prints the leaderboard by utilizing the readFile() method from HandleFile.
     */
    public static void printLeaderboard(){
        clearScreen();
        HandleFile.readFile("leaderboard.txt");
        System.out.println("Press any key to continue");
    }

    /**
     * Prints the main gameScreen, with information to the user like the Hangman Ascii-art and amount of guesses + the displayWord and previous guesses.
     */
    public static void printGameScreen(){
        clearScreen();
        System.out.println();
        System.out.println("Hangman");
        Interface.displayHangman();
        System.out.println("Current guess: " + (Model._charGuess.size()));
        System.out.println(Model._displayWord);
        if(Model._infoForUser.length() > 0){
            System.out.println(Model._infoForUser);
        }
        if(Model._charGuess.size() > 0){
            System.out.println(Model._charGuess);
        }
    }

    /**
     * "Clears" the screen. Prints 20 empty lines, to mimic clearing the screen.
     */
    public static void clearScreen(){
        for(int i = 0; i < 20; i++){
            System.out.println();
            System.out.flush();
        }
    }

    /**
     * Prints the screen which the user is greeted with upon victory. Contains various information which might be of value to the user
     * + a menu with options on what to do next.
     */
    public static void printVictoryScreen(){
        clearScreen();
        System.out.println();
        System.out.println("-+Victory+-");
        System.out.println("You won");
        System.out.println("The answer was: " + Model.get_answer());
        System.out.println("Amount of guesses: " + (Model._charGuess.size()));
        System.out.println("Amount of incorrect guesses: " + Model._wrongGuessesAmount);
        if(Model._charGuess.size() > 0){
            System.out.println(Model._charGuess);
        }
        System.out.println("-----------------");
        System.out.println("1. Play again");
        System.out.println("2. Add to leaderboard");
        System.out.println("3. Exit");
    }

    /**
     * Prints the screen which the user is greeted with upon losing. Contains various information which might be of value to the user
     * + a menu with options on what to do next.
     */
    public static void printGameOverScreen(){
        clearScreen();
        System.out.println();
        System.out.println("-+Game Over+-");
        System.out.println("You lost");
        System.out.println("The answer was: " + Model.get_answer());
        System.out.println("Your guess was: " + Model._displayWord);
        System.out.println("Amount of guesses: " + (Model._charGuess.size()));
        System.out.println("Amount of incorrect guesses: " + Model._wrongGuessesAmount);
        System.out.println(Model._charGuess);
        System.out.println("-----------------");
        System.out.println("1. Play again");
        System.out.println("2. Exit");
    }

    /**
     * Holds an ArrayList with Ascii-art of a Hangman character. When called it will print a character on a position based on the amount of incorrect guesses.
     */
    public static void displayHangman(){
        ArrayList<String> str = new ArrayList<String>();
        str.add("  +---+\n" +
                "  |   |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "=========");
        str.add("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "=========");
        str.add("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                "  |   |\n" +
                "      |\n" +
                "      |\n" +
                "=========");
        str.add("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|   |\n" +
                "      |\n" +
                "      |\n" +
                "=========");
        str.add("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                "      |\n" +
                "      |\n" +
                "=========");
        str.add("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                " /    |\n" +
                "      |\n" +
                "=========");
        str.add("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                " / \\  |\n" +
                "      |\n" +
                "=========");
        System.out.println(str.get(Model._wrongGuessesAmount));
    }
}

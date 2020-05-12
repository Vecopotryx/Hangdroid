public class GameControl {
    /**
     * This method is what keeps the game running. It sets up some basic values, like the answer by calling the randomFromFile method in HandleFile.
     * Then proceeds to check if the user has lost or won, if that's not the case. Then the game proceeds by calling the gameScreen method
     */
    public static void startGame(){
        HandleFile.randomFromFile("wordlist.txt");
        populateArray();
        while(true){
            updateDisplayWord();
            if(Model._displayWord.equals(Model.get_answer())){
                victoryScreen();
                break;
            } else if(Model._wrongGuessesAmount == 6) {
                lossScreen();
                break;
            } else {
                gameScreen();
            }
        }
    }

    /**
     * Handles the logic for what the user sees and interacts with after they've won a game.
     */
    public static void victoryScreen(){
        Interface.printVictoryScreen();
        switch(Input.getIntegerInput()){
            case 1:
                clearVariables();
                MenuControl.start();
                break;
            case 2:
                addToLeaderboard();
                break;
            case 3:
                System.out.println("Goodbye");
                System.exit(0);
        }
    }

    /**
     * Handles the logic for what the user sees and interacts with after they've lost a game.
     */
    public static void lossScreen() {
        Interface.printGameOverScreen();
        switch (Input.getIntegerInput()) {
            case 1:
                clearVariables();
                MenuControl.menu();
                break;
            case 2:
                System.out.println("Goodbye");
                System.exit(0);
            default:
                clearVariables();
                MenuControl.start();
                break;
        }
    }

    /**
     * Handles the logic for adding a line to the leaderboard. Utilizes the writeToFile() method from HandleFile.
     */
    public static void addToLeaderboard(){
        System.out.print("Enter name: ");
        String name = Input.getStringInput();
        try{
            HandleFile.writeToFile("leaderboard.txt", name + " - " + Model._wrongGuessesAmount + " wrong guesses");

        } catch(Exception E) {
            System.out.println("An error occurred");
        }
        System.out.println("1. Go again");
        System.out.println("2. Exit");
        switch(Input.getIntegerInput()) {
            case 1:
                clearVariables();
                MenuControl.start();
                break;
            case 2:
                System.out.println("Goodbye");
                System.exit(0);
                break;
        }
    }

    /**
     * Clears the variables, useful if the user wants to play again after having won or lost, in order to avoid stuff carrying over from the previous game.
     */
    public static void clearVariables(){
        Model.set_answer("");
        Model._wrongGuessesAmount = 0;
        Model._answerArray.clear();
        Model._charGuess.clear();
        Model._infoForUser = "";
        Model._displayWord = "";
        Model._customMinLength = 1;
        Model._customMaxLength = 12;
    }

    /**
     * This method handles the main logic for the screen that the user sees during the game.
     * It gets user input and compares it against a few conditions in order to act correctly.
     */
    public static void gameScreen(){
        Interface.printGameScreen();
        String stringIn = Input.getStringInput();
        if(stringIn.length() > 1){
            Model._infoForUser = "This application only supports entering a valid char";
        } else {
            if(!stringIn.equals("")){
                if(Model._charGuess.contains((stringIn.toCharArray()[0]))){
                    Model._infoForUser = "You've already guessed that";
                    gameScreen();
                } else if(!(Model._answerArray.contains((stringIn.toCharArray()[0])))) {
                    Model._charGuess.add(stringIn.toCharArray()[0]);
                    Model._wrongGuessesAmount += 1;
                    Model._infoForUser = "";
                } else {
                    Model._charGuess.add(stringIn.toCharArray()[0]);
                    Model._infoForUser = "";
                }
            }
        }
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
                if(Model.get_answer().toCharArray()[i] == Model._charGuess.get(c)){
                    Model._displayWord += Model._charGuess.get(c) + "";
                    isYes = true;
                }
            if(!isYes){
                Model._displayWord += " _ ";
            }

        }
    }

    /**
     * Handles the logic that lets the user pick various settings for the game. If this method isn't  called then the game proceeds with default settings (completely random)
     * Displays a menu from the Interface class and then uses a switch to compare user input with various cases, and then acts accordingly.
     */
    public static void setCustomSettings(){
        Interface.clearScreen();
        Interface.printCustomSettings();
        switch(Input.getIntegerInput()){
            case 1:
                System.out.println("Easy selected");
                Model._customMinLength = 1;
                Model._customMaxLength = 3;
                startGame();
                break;
            case 2:
                System.out.println("Medium selected");
                Model._customMinLength = 3;
                Model._customMaxLength = 5;
                startGame();
                break;
            case 3:
                System.out.println("Difficult selected");
                Model._customMinLength = 5;
                Model._customMaxLength = 8;
                startGame();
                break;
            case 4:
                Interface.printPickCustomSpan("minimum");
                Model._customMinLength = Input.getIntegerInput();
                if(Model._customMinLength > 6){
                    System.out.println("Maximum supported minimum length is 6");
                    Model._customMinLength = 6;
                }
                Interface.printPickCustomSpan("maximum");
                Model._customMaxLength = Input.getIntegerInput();
                while(Model._customMaxLength < Model._customMinLength){
                    System.out.println("Maximum length can't be smaller than the minimum length");
                    Interface.printPickCustomSpan("maximum");
                    Model._customMaxLength = Input.getIntegerInput();
                }
                startGame();
            default:
                Interface.clearScreen();
                setCustomSettings();
        }
    }

    /**
     * Populates the _answerArray with the contents of the _answer string. Useful in order to simplify comparing guesses with the answer.
     */
    public static void populateArray(){
        for(char c : Model.get_answer().toCharArray()) {
            Model._answerArray.add(c);
        }
    }
}

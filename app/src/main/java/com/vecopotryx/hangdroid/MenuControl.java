public class MenuControl {
    /**
     * Responsible for starting the application and calling the menu
     */
    public static void start(){
        menu();
    }

    /**
     * This method handles the greeting menu. Calls the printMenu method from the Interface class,
     * then checks for input using getIntegerInput from the Input class and acts upon what the user picks.
     */
    public static void menu(){
        Interface.printMenu();
        switch(Input.getIntegerInput()){
            case 1:
                GameControl.startGame();
                break;
            case 2:
                GameControl.setCustomSettings();
                break;
            case 3:
                Interface.printLeaderboard();
                if(Input.getStringInput().length()>=0){
                    start();
                }
                break;
            case 4:
                System.out.println("Goodbye");
                System.exit(0);
            default:
                Interface.clearScreen();
                start();
        }
    }

}

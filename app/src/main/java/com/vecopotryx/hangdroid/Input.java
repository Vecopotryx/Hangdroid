import java.util.Scanner;

public class Input {
    /**
     * This method is used to get user input to an Integer. In order to prevent crashes, it checks if the value entered can be stored in an Integer.
     * If not, it will prompt the user to enter a valid value.
     * @return Returns the input value in an Integer.
     */
    public static Integer getIntegerInput(){
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt()) {
            System.out.println("Enter a valid 32-bit integer value");
            input.next();
        }
        return input.nextInt();
    }

    /**
     * This method is used to get user input to a String. In order to prevent crashes, it checks if the value entered can be stored in a String.
     * If not, it will prompt the user to enter a valid value.
     * @return Returns the input value in a String.
     */
    public static String getStringInput(){
        Scanner input = new Scanner(System.in);
        while (!input.hasNextLine()) {
            System.out.println("Enter a valid value");
            input.next();
        }
        return input.nextLine();
    }
}

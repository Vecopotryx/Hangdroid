import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class HandleFile {
    /**
     * A method which prints the contents of a file. Used for printing the leaderboard.
     * inspired by https://www.w3schools.com/java/java_files_read.asp
     * @param fileName The name of the file which is to be read/printed
     */
    public static void readFile(String fileName){
        File fileIn = new File(fileName);
        if(fileIn.exists()){
            try {
                Scanner reader = new Scanner(fileIn);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    System.out.println(data);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File doesn't exist");
        }
    }

    /**
     * Gets a random line from a file and then stores it as the answer.
     * @param fileName The file which is to be read
     */
    public static void randomFromFile(String fileName) {
        Random random = new Random();
        File fileIn = new File(fileName);
        if(fileIn.exists()){
            try {
                String data = "java";
                Scanner reader = new Scanner(fileIn);
                for(int i = 0; i < random.nextInt(1000); i++){
                    data = reader.nextLine();
                }
                if(Model._customMinLength <= data.length() && data.length() <= Model._customMaxLength){
                    Model.set_answer(data);
                } else {
                    randomFromFile(fileName);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File doesn't exist");
        }
    }

    /**
     * A method which writes a line of text to a file. Useful for writing to the leaderboard.
     * Utilizing https://howtodoinjava.com/java/io/java-append-to-file/
     * @param fileName The file which is to be modified.
     * @param textIn The text to write.
     */
    public static void writeToFile(String fileName, String textIn) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName, true)
        );
        writer.newLine();
        writer.write(textIn);
        writer.close();
    }
}

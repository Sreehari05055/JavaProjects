import java.util.Scanner;

/**
 * The Driver class contains the main method to initiate the CleanMusic
 * and CleanDownloads processes based on user input.
 */
public class MainCleaner {

    /**
     * The main method to run the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for the username once
        System.out.print("Enter file holder name: ");
        String holderName = scanner.nextLine().strip();

        // Initialize the CleanMusic and CleanDownloads and CleanDocuments classes with the username
        new CleanMusic(holderName);
        new CleanDownloads(holderName);
        new CleanDocuments(holderName);

        // Indicate the process is complete
        System.out.println("Process Completed");

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}


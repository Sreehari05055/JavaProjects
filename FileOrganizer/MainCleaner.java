import java.util.Scanner;

public class MainCleaner{
    // Driver class main method
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Ask for the username once
        System.out.print("Enter file holder name: ");
        String holderName = scanner.nextLine().strip();

        // Pass the username to both classes
         new CleanMusic(holderName);
         new CleanDownloads(holderName);
        System.out.println("Process Completed");

    }
}


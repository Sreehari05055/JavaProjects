import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * The CleanDownloads class organizes files in the Downloads directory
 * by moving audio, video, and document files to their respective directories.
 */
public class CleanDownloads {

    private String userFileName;
    public String publicDir;
    public String audioDir;
    public String videoDir;
    private File obj;
    private File[] files;

    /**
     * Constructs a CleanDownloads instance, initializing directory paths
     * for organizing files based on the provided username.
     *
     * @param holderName the username to set
     */
    public CleanDownloads(String holderName) {
        holderName(holderName.strip());
        publicDir = "/Users/" + userFileName + "/Documents";
        audioDir = "/Users/" + userFileName + "/Music";
        videoDir = "/Users/" + userFileName + "/Pictures";
        obj = new File("/Users/" + userFileName + "/Downloads");

        files = obj.listFiles();
        furtherProcessing(files);
    }

    /**
     * Processes the files in the Downloads directory, moving them to their
     * respective directories based on their file extensions.
     *
     * @param obj the array of files to process
     */
    public void furtherProcessing(File[] obj) {
        try{
        if (obj != null) {
            for (File file : obj) {
                String fileName = file.getName().toLowerCase();

                // Move audio files
                if (fileName.endsWith(".mp3") || fileName.endsWith(".m4a") || fileName.endsWith(".aac") || fileName.endsWith(".wav")) {
                    moveFile(file, audioDir);
                }
                // Move video files
                else if (fileName.endsWith(".mp4") || fileName.endsWith(".jpeg") || fileName.endsWith(".jpg") || fileName.endsWith(".png")|| fileName.endsWith(".gif")) {
                    moveFile(file, videoDir);
                }
                // Move document files
                else if (fileName.endsWith(".zip") || fileName.endsWith(".doc") ||
                        fileName.endsWith(".docx") || fileName.endsWith(".txt") ||
                        fileName.endsWith(".pdf") || fileName.endsWith(".xls")
                        || fileName.endsWith(".xlsx") || fileName.endsWith(".ppt")
                        || fileName.endsWith(".pptx")) {
                    moveFile(file, publicDir);
                }
            }
        }}catch (Exception e) {
            System.out.println("Error Transferring Files: "+e.getMessage());}

    }

    /**
     * Moves a file to the specified target directory, creating the directory
     * if it does not exist.
     *
     * @param file    the file to move
     * @param dirPath the target directory path
     */
    protected static void moveFile(File file, String dirPath) {
        Path targetDirPath = Paths.get(dirPath);

        try {
            // Create the target directory if it doesn't exist
            if (Files.notExists(targetDirPath)) {
                Files.createDirectory(targetDirPath);
                System.out.println("Directory created: " + targetDirPath);
            }

            // Move the file to the target directory
            Files.move(file.toPath(), targetDirPath.resolve(file.getName()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error moving file: " + e.getMessage());
        }
    }

    /**
     * Sets the username for the current instance.
     *
     * @param name the username to set
     */
    public void holderName(String name) {
        this.userFileName = name;
    }
}

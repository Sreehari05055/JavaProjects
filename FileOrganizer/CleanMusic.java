import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * The CleanMusic class is responsible for organizing music files in a user's Music directory.
 * It prompts the user for their username and organizes files based on their extensions.
 */
public class CleanMusic

{   private String userName;
    public String docDir;
    public String audDir;
    public String vidDir;
    public String dldDir;
    File musicObj;
     File [] musicFiles;
    /**
     * Constructs a CleanMusic instance, initializing directory paths for organizing files
     * based on the provided username.
     *
     * @param holderName the username to set
     */
    public CleanMusic(String holderName)
    {
        Holder(holderName.strip());
        dldDir = "/Users/"+userName+"/Downloads";
        docDir = "/Users/"+userName+"/Documents";
        audDir = "/Users/"+userName+"/Downloads";
        vidDir = "/Users/"+userName+"/Pictures";

        musicObj = new File("/Users/"+userName+"/Music");

        musicFiles = musicObj.listFiles();
        this.FurtherProcess(musicFiles);
    }
    /**
     * Sets the username for the current instance.
     *
     * @param name the username to set
     */
    public void Holder(String name){
        this.userName = name;
    }

    /**
     * Processes the music files, moving them to the appropriate directories based on their extensions.
     *
     * @param obj2 the array of music files to process
     */
    public void FurtherProcess(File[] obj2) {
        if (obj2 != null) {
            for (File file : obj2) {
                //Path targetPDirPath;

                // Check if the file is not a video or image
                if (!file.getName().toLowerCase().endsWith(".mp4")
                        && !file.getName().toLowerCase().endsWith(".jpeg")
                        && !file.getName().toLowerCase().endsWith(".jpg")) {

                    // Check if the file is a document or archive
                    if (file.getName().toLowerCase().endsWith(".zip")
                            || file.getName().toLowerCase().endsWith(".doc")
                            || file.getName().toLowerCase().endsWith(".docx")
                            || file.getName().toLowerCase().endsWith(".txt")
                            || file.getName().toLowerCase().endsWith(".pdf")) {
                        moveFileToDirectory(file, this.docDir);
                    }
                } else {
                    // Move video or image files to the video directory
                    moveFileToDirectory(file, this.vidDir);
                }
            }
        }
    }

    /**
     * Moves a file to the specified directory, creating the directory if it doesn't exist.
     *
     * @param file    the file to move
     * @param dirPath the target directory path as a string
     */
    private void moveFileToDirectory(File file, String dirPath) {
        Path targetPDirPath = Paths.get(dirPath);

        try {
            // Check if the target directory exists; if not, create it
            if (Files.notExists(targetPDirPath, new LinkOption[0])) {
                Files.createDirectory(targetPDirPath);
                System.out.println("Directory created: " + targetPDirPath);
            }

            // Move the file to the target directory
            Files.move(file.toPath(), targetPDirPath.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error moving file: " + e.getMessage());
        }
    }
}

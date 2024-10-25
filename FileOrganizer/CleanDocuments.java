import java.io.File;
public class CleanDocuments
{
    private String userFileName;
    public String audioDir;
    public String videoDir;
    public String downloadsDir;

    File docObj;
    File [] docFiles;

    /**
     * Constructs a CleanDocuments instance, initializing directory paths
     * for organizing files based on the provided username.
     *
     * @param holder the username to set
     */
    public CleanDocuments(String holder) {
        holderName(holder.strip());
        downloadsDir = "/Users/" + userFileName + "/Downloads";
        audioDir = "/Users/" + userFileName + "/Music";
        videoDir = "/Users/" + userFileName + "/Pictures";
        docObj = new File("/Users/" + userFileName + "/Documents");

        docFiles = docObj.listFiles();
        furtherProcess(docFiles);
    }

    public void furtherProcess(File[] obj) {
        try {


        if (obj != null) {
            for (File file : obj) {
                String fileName = file.getName().toLowerCase();

                // Move audio files
                if (fileName.endsWith(".mp3") || fileName.endsWith(".m4a") || fileName.endsWith(".aac") || fileName.endsWith(".wav")) {
                    CleanDownloads.moveFile(file, audioDir);
                }
                // Move video and pictures files
                else if (fileName.endsWith(".mp4") || fileName.endsWith(".jpeg") || fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
                    CleanDownloads.moveFile(file, videoDir);
                }
                // Move download files
                else if (fileName.endsWith(".dmg") || fileName.endsWith(".exe") ||
                        fileName.endsWith(".bin") || fileName.endsWith(".sh") ||
                        fileName.endsWith(".py") || fileName.endsWith(".json") ||
                        fileName.endsWith(".xml") || fileName.endsWith(".yaml") ||
                        fileName.endsWith(".html") || fileName.endsWith(".css") ||
                        fileName.endsWith(".log") || fileName.endsWith(".js")) {
                   CleanDownloads.moveFile(file, downloadsDir);
                }
            }
        }}catch (Exception e) {
            System.out.println("Error Transferring Files: "+e.getMessage());}

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

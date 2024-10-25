import java.io.File;

/**
 * The CleanMusic class is responsible for organizing music files in a user's Music directory.
 * It prompts the user for their username and organizes files based on their extensions.
 */
public class CleanMusic

{   private String userName;
    public String docDir;
    public String audDir;
    public String vidDir;
    public String downloadsDir;
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
        downloadsDir = "/Users/"+userName+"/Downloads";
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
     * Processes the music dir files, moving them to the appropriate directories based on their extensions.
     *
     * @param obj2 the array of music files to process
     */
    public void FurtherProcess(File[] obj2) {
       try{
        if (obj2 != null) {
            for (File file : obj2) {
                String fileName = file.getName().toLowerCase();

                // Check if the file is a document or archive
                    if (fileName.endsWith(".zip") || fileName.endsWith(".doc") ||
                            fileName.endsWith(".docx") || fileName.endsWith(".txt") ||
                            fileName.endsWith(".pdf") || fileName.endsWith(".xls")
                            || fileName.endsWith(".xlsx") || fileName.endsWith(".ppt")
                            || fileName.endsWith(".pptx")) {
                        CleanDownloads.moveFile(file, this.docDir);
                    }
                    // Move video and picture files
                    else if (fileName.endsWith(".mp4") || fileName.endsWith(".jpeg") ||
                            fileName.endsWith(".jpg") || fileName.endsWith(".png") ||
                            fileName.endsWith(".gif")) {
                        CleanDownloads.moveFile(file, this.vidDir);
                    }
                    // Move download files
                    else if (fileName.endsWith(".dmg") || fileName.endsWith(".exe") ||
                            fileName.endsWith(".bin") || fileName.endsWith(".sh") ||
                            fileName.endsWith(".py") || fileName.endsWith(".json") ||
                            fileName.endsWith(".xml") || fileName.endsWith(".yaml") ||
                            fileName.endsWith(".html") || fileName.endsWith(".css") ||
                            fileName.endsWith(".log") || fileName.endsWith(".js")) {
                        CleanDownloads.moveFile(file, this.downloadsDir);
                    }
            }
        }}catch (Exception e){
           System.out.println("Error Transferring Files: "+e.getMessage());
       }
    }
}

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class CleanDownloads {
    private String userFileName;
    public String publicDir;
    public String audioDir;
    public String videoDir;
    File Obj;
     File [] files;
    // Class Constructor
    public CleanDownloads(String holderName) {

        HolderName(holderName.strip());
        publicDir = "/Users/"+userFileName+"/Documents";
        audioDir = "/Users/"+userFileName+"/Music";
        videoDir = "/Users/"+userFileName+"/Pictures";
        Obj = new File("/Users/"+userFileName+"/Downloads");

        files = Obj.listFiles();
        FurtherProcessing(files);

    }
    public void FurtherProcessing(File[] obj){
        if(obj != null){
            for(File file: obj){
                if(file.getName().toLowerCase().endsWith(".mp3") || file.getName().toLowerCase().endsWith(".m4a")){
                    try{
                        Path targetDirPath = Paths.get(audioDir);
                        if (Files.notExists(targetDirPath)) {
                            try {
                                Files.createDirectory(targetDirPath);
                                System.out.println("Directory created: " + targetDirPath);
                        } catch (IOException e) {
                                System.err.println("Error creating directory: " + e.getMessage());
                            }
                            if (Files.isDirectory(targetDirPath)) {
                                try {
                                    Files.move(file.toPath(), targetDirPath.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
                                } catch (IOException e) {
                                    System.err.println("Error moving file: " + e.getMessage());
                                }
                            }

                        }
                    else{
                        Files.move(file.toPath(),targetDirPath.resolve(file.getName()),StandardCopyOption.REPLACE_EXISTING);
                    }
                    }
                    catch (IOException E){
                        System.err.println("Error: " + E.getMessage());
                        E.printStackTrace();
                    }
            }else if(file.getName().toLowerCase().endsWith(".mp4") || file.getName().toLowerCase().endsWith(".jpeg") || file.getName().toLowerCase().endsWith(".jpg")){
                    try{
                        Path targetDirVPath = Paths.get(videoDir);
                        if (Files.notExists(targetDirVPath)) {
                            try {
                                Files.createDirectory(targetDirVPath);
                                System.out.println("Directory created: " + targetDirVPath);
                            } catch (IOException e) {
                                System.err.println("Error creating directory: " + e.getMessage());
                            }
                            if (Files.isDirectory(targetDirVPath)) {
                                try {
                                    Files.move(file.toPath(), targetDirVPath.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
                                } catch (IOException e) {
                                    System.err.println("Error moving file: " + e.getMessage());
                                }
                            }
                        }
                        else{
                            Files.move(file.toPath(),targetDirVPath.resolve(file.getName()),StandardCopyOption.REPLACE_EXISTING);
                    }}catch (IOException e){
                        System.out.println("Error: "+e.getMessage());
                        e.printStackTrace();
                    }
                }else if(file.getName().toLowerCase().endsWith(".zip") || file.getName().toLowerCase().endsWith(".doc") || file.getName().toLowerCase().endsWith(".docx") || file.getName().toLowerCase().endsWith(".txt") || file.getName().toLowerCase().endsWith(".pdf")){
                    try{
                        Path targetPDirPath = Paths.get(publicDir);
                        if (Files.notExists(targetPDirPath)) {
                            try {
                                Files.createDirectory(targetPDirPath);
                                System.out.println("Directory created: " + targetPDirPath);
                            } catch (IOException e) {
                                System.err.println("Error creating directory: " + e.getMessage());
                            }
                                if (Files.isDirectory(targetPDirPath)) {
                                    try {
                                        Files.move(file.toPath(), targetPDirPath.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
                                    } catch (IOException e) {
                                        System.err.println("Error moving file: " + e.getMessage());
                                    }
                                }
                            }
                        else{
                            Files.move(file.toPath(),targetPDirPath.resolve(file.getName()),StandardCopyOption.REPLACE_EXISTING);
                        }
                    }
                    catch (IOException e){
                        System.out.println("Error: "+e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }

    }
    public void HolderName(String name){
        this.userFileName = name;
    }
}



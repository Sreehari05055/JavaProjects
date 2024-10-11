import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CleanMusic

{   private String userName;
    public String docDir;
    public String audDir;
    public String vidDir;
    public String dldDir;
    File musicObj;
     File [] musicFiles;
     //Class Constructor
    public CleanMusic(String holderName)
    {
        Holder(holderName.strip());
        dldDir = "/Users/"+userName+"/Downloads";
        docDir = "/Users/"+userName+"/Documents";
        audDir = "/Users/"+userName+"/Downloads";
        vidDir = "/Users/"+userName+"/Pictures";

        musicObj = new File("/Users/"+userName+"/Music");

        musicFiles = musicObj.listFiles();
        FurtherProcess(musicFiles);
    }
    public void Holder(String name){
        this.userName = name;
    }
    public void FurtherProcess(File [] obj2)
    {
        if(obj2 != null)
        {
            for(File file: obj2)
            {
                if(file.getName().toLowerCase().endsWith(".mp4") || file.getName().toLowerCase().endsWith(".jpeg") || file.getName().toLowerCase().endsWith(".jpg")){
                    try{
                        Path targetDirVPath = Paths.get(vidDir);
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
                        Path targetPDirPath = Paths.get(docDir);
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
                        }}catch (IOException e){
                        System.out.println("Error: "+e.getMessage());
                        e.printStackTrace();
                    }

        }
    }
}
    }
}

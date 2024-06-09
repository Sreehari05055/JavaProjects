import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

public class Downloads {
    private String userFileName = "sreehari";
    public String publicDir = "/Users/"+userFileName+"/Public";
    public String audioDir = "/Users/"+userFileName+"/Music";
    public String videoDir = "/Users/"+userFileName+"/Pictures";
File Obj = new File("/Users/"+userFileName+"/Downloads");
File[] files;

    public Downloads() {
        files = Obj.listFiles();
        furtherProcessing(files);
    }
    public void furtherProcessing(File[] obj){
        if(obj != null){
            for(File file: obj){
                if(file.getName().toLowerCase().endsWith(".mp3") || file.getName().toLowerCase().endsWith(".m4a")){
                    try{
                        Path targetDirPath = Paths.get(audioDir);
                        if (Files.notExists(targetDirPath)) {
                            Files.createDirectory(targetDirPath);
                            Files.move(file.toPath(),targetDirPath.resolve(file.getName()),StandardCopyOption.REPLACE_EXISTING);}
                    else{
                        Files.move(file.toPath(),targetDirPath.resolve(file.getName()),StandardCopyOption.REPLACE_EXISTING);
                    }
                    }
                    catch (IOException E){
                        System.err.println("Access Denied: " + E.getMessage());
                        E.printStackTrace();
                    }
            }else if(file.getName().toLowerCase().endsWith(".mp4") || file.getName().toLowerCase().endsWith(".jpeg")){
                    try{
                        Path targetDirVPath = Paths.get(videoDir);
                        if (Files.notExists(targetDirVPath)) {
                            Files.createDirectory(targetDirVPath);
                            Files.move(file.toPath(),targetDirVPath.resolve(file.getName()),StandardCopyOption.REPLACE_EXISTING);}
                        else{
                            Files.move(file.toPath(),targetDirVPath.resolve(file.getName()),StandardCopyOption.REPLACE_EXISTING);
                    }}catch (IOException e){
                        System.out.println("Error: "+e.getMessage());
                        e.printStackTrace();
                    }
                }else if(file.getName().toLowerCase().endsWith(".zip") || file.getName().toLowerCase().endsWith(".doc") || file.getName().toLowerCase().endsWith(".docx") || file.getName().toLowerCase().endsWith(".txt")){
                    try{
                        Path targetPDirPath = Paths.get(publicDir);
                        if (Files.notExists(targetPDirPath)) {
                            Files.createDirectory(targetPDirPath);
                            Files.move(file.toPath(),targetPDirPath.resolve(file.getName()),StandardCopyOption.REPLACE_EXISTING);}
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
class Documents{

}

class MainCleaner{
    public static void main(String[] args) {
        new Downloads();
        //new Documents();
    }
}
package redoc.anh.lehoang;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Created by Hoang Anh on 24-Feb-16.
 */
public class RenameFileInDirectory {
    private String rootFilePath = "";
    private int recursiveDepth = 1;
    public RenameFileInDirectory(){}
    public RenameFileInDirectory(String rootFilePath, int recursiveDepth){
        this.rootFilePath = rootFilePath;
        this.recursiveDepth = recursiveDepth;
    }

    public void recursive(String filePath){
        File rootFile = new File(filePath);
        if(rootFile.isDirectory() && (recursiveDepth >= 1)){
            File[] listFiles = rootFile.listFiles();
            if(listFiles != null){
                --recursiveDepth;
                for (File file: listFiles){
                    String subFilePath = file.getParent() + File.separator + file.getName();
                    recursive(subFilePath);
                }
            }
            rename(rootFile);
        }else{
            rename(rootFile);
        }
    }

    private static final Pattern HAS_PUNCTUATION_SPACE = Pattern.compile("[\\p{Punct}\\s]");
    public void rename(File file) {
        String filePath = file.getParent();
        String fileName = file.getName();
        String fileBaseName = FilenameUtils.getBaseName(fileName);
        String fileExtension = FilenameUtils.getExtension(fileName);

        String newFileBaseName = fileBaseName.toLowerCase().replaceAll(HAS_PUNCTUATION_SPACE.pattern(), "-");
        String newFileName = "";
        if(file.isDirectory()){
            newFileName = newFileBaseName;
        }else{
            newFileName = newFileBaseName + "." + fileExtension;
        }

        File newFile = new File(filePath, newFileName);
        int i = 1;
        while(newFile.exists()){
            newFileBaseName = newFileBaseName + "-" + i;
            newFileName = newFileBaseName + "." + fileExtension;
            newFile = new File(filePath, newFileName);
            i++;
        }
//        boolean fileDelete_status = newFile.delete();
        boolean rename_status = file.renameTo(newFile);
    }

    public void run(){
        recursive(rootFilePath);
    }
}

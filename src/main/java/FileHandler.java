import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

public class FileHandler {
    private File dataFolder = new File("C:/path/to/data");

    public FileHandler() {
        this.dataFolder = resolveDefaultDataFolder();
    }

    public FileHandler(File dataFolder) {
        if (dataFolder != null)
            this.dataFolder = dataFolder;
    }

    private File resolveDefaultDataFolder() {
        File rootDataFolder = new File("data");
        if (rootDataFolder.exists() && rootDataFolder.isDirectory())
            return rootDataFolder;

        File srcMainDataFolder = new File("src/main/data");
        if (srcMainDataFolder.exists() && srcMainDataFolder.isDirectory())
            return srcMainDataFolder;

        return rootDataFolder;
    }

    // Returns an Array List of all the files inside the data folder
    public ArrayList<String> getFileList(){
        ArrayList<String> fileNames = new ArrayList<>();
        File[] files = dataFolder.listFiles();
        if (files == null)
            return fileNames;

        for (File file : files)
            if (file.isFile() && file.getName().endsWith(".txt"))
                fileNames.add(file.getName());

        Collections.sort(fileNames);

        return fileNames;
    }

    // Takes a file index as input and returns the file's contents
    public String readFile(int fileNum) {
        ArrayList<String> files = getFileList();
        if (fileNum < 1 || fileNum > files.size())
            return "File Number not Found";

        return readFile(files.get(fileNum - 1));
    }

    private String readFile(String fileName) {
        if (fileName == null || fileName.trim().isEmpty())
            return "File Location Not Found";

        File currFile = new File(dataFolder, fileName.trim());
        if (!currFile.exists() || !currFile.isFile())
            return "File Location Not Found";

        try {
            return Files.readString(currFile.toPath());
        } catch (IOException e) {
            return "File Location Not Found";
        }
    }
}

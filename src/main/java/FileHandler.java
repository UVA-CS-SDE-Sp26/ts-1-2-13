import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

public class FileHandler {
    private File dataFolder = new File("C:/path/to/data");

    public FileHandler() {
    }

    public FileHandler(File dataFolder) {
        if (dataFolder != null)
            this.dataFolder = dataFolder;
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

    // Takes a file index as an input and returns a string of the file's contents
    public String readFile(int fileNum) throws FileNotFoundException {
        ArrayList<String> files = getFileList();
        if (files == null || fileNum < 1 || fileNum > files.size())
            return "File Number not Found";
        else
            return readFile(files.get(fileNum-1));
    }

    // Takes a file name as an input and returns a string of the file's contents
    public String readFile(String fileName) throws FileNotFoundException {
        try {
            File currFile = new File(dataFolder, fileName);
            if (!currFile.exists() || !currFile.isFile())
                return "File Location Not Found";

            return Files.readString(currFile.toPath());
        } catch (IOException e) {
            return "File Location Not Found";
        }
    }
}

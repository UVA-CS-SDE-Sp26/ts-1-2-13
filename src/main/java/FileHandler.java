import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class FileHandler {
    private File dataFolder = new File("data");

    // Returns an Array List of all the files inside the data folder
    public ArrayList<String> getFileList(){
        ArrayList<String> fileNames = new ArrayList<>();
        File[] files = dataFolder.listFiles();


        for(File file: files)
            fileNames.add(file.getName());

        return fileNames;
    }

    // Takes a file index as an input and returns a string of the file's contents
    public String readFile(int fileNum) throws FileNotFoundException {
        ArrayList<String> files = getFileList();
        if(fileNum > files.size() || fileNum < 1)
            return "File Index is Invalid";
        else
            return readFile(files.get(fileNum-1));
    }

    // Takes a file name as an input and returns a string of the file's contents
    public String readFile(String fileName) throws FileNotFoundException {

        try {
            File currFile = new File(dataFolder, fileName);
            Scanner reader = new Scanner(currFile);
            String content = "";

            while (reader.hasNextLine()) {
                content += reader.nextLine();
            }
            reader.close();
            return content;
        } catch (FileNotFoundException e) {
            return "File Location Not Found";
        }

    }

}

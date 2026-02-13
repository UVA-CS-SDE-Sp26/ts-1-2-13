import java.io.File;
import java.util.ArrayList;

public class TopSecret {
    public static void main(String[] args) {
        try {
            FileHandler fileHandler = new FileHandler(getDefaultDataFolder());

            if (args.length == 0) {
                printFileList(fileHandler);
                return;
            }

            int fileNumber = Integer.parseInt(args[0]);
            if (args.length > 2 || !isNumeric(args[0]) || fileNumber < 1) {
                System.out.println("Invalid input");
                return;
            }

            String content = fileHandler.readFile(fileNumber);
            if (content.equals("File Number not Found") || content.equals("File Location Not Found")) {
                System.out.println(content);
                return;
            }

            String keyPath;
            if (args.length == 2)
                keyPath = getKeyPath(args[1]);
            else
                keyPath = getKeyPath("key.txt");
            Cipher cipher = new Cipher(keyPath);
            System.out.println(cipher.decipher(content));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void printFileList(FileHandler fileHandler) {
        ArrayList<String> files = fileHandler.getFileList();
        if (files.isEmpty()) {
            System.out.println("No files available");
            return;
        }

        for (int i = 0; i < files.size(); i++)
            System.out.println(i + 1 + " " + files.get(i));
    }

    private static File getDefaultDataFolder() {
        File folder = new File("../data");
        if (folder.exists())
            return folder;

        folder = new File("src/main/data");
        if (folder.exists())
            return folder;

        return new File("data");
    }

    private static String getKeyPath(String fileName) {
        String path = "../../../ciphers/" + fileName;
        if (new File(path).exists())
            return path;

        return "ciphers/key.txt";
    }

    private static boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

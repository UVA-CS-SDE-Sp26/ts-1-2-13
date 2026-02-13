import java.util.ArrayList;

/**
 * Commmand Line Utility
 */
public class TopSecret {
    private static final String DEFAULT_KEY_PATH = "ciphers/key.txt";

    public static void main(String[] args) {
        try {
            ProgramControl program = new ProgramControl();
            // if ProgramControl does work in its constructor, this will catch it
            // if it has a method later, call it here
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        FileHandler fileHandler = new FileHandler();

        if (args.length == 0) {
            printFileList(fileHandler);
            return;
        }

        if (args.length > 2) {
            System.out.println("Invalid input: too many inputs");
            return;
        }

        if (!isNumeric(args[0])) {
            System.out.println("Invalid input: file number needs to be numeric");
            return;
        }

        int fileNumber = Integer.parseInt(args[0]);
        if (fileNumber < 1) {
            System.out.println("Invalid input: file number must be 1 or greater");
            return;
        }

        try {
            String content = fileHandler.readFile(fileNumber);
            if (content.equals("File Number not Found") || content.equals("File Location Not Found")) {
                System.out.println(content);
                return;
            }

            String keyPath = args.length == 2 ? args[1] : DEFAULT_KEY_PATH;
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
            System.out.printf("%02d %s%n", i + 1, files.get(i));
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

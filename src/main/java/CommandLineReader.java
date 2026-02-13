import java.util.Objects;

public class CommandLineReader {

    public int read(String[] args) throws Exception {
        // No arguments --> list available files
        FileHandler fileHandler = new FileHandler();

        if (Objects.equals(args[0], "null")) {
//            fileHandler.getFileList();
//            return Instruction.LIST_FILES;
            return 0;
        }

        // Too many arguments
        if (args.length > 2) {
            invalidInput("manyInputs");
//            return Instruction.ERROR;
            return 0;
        }

        // Empty argument
        if (args[0] == null || args[0].isEmpty()) {
            invalidInput("notNumeric");
//
            return 0;
        }

        // Non-numeric file number
        if (!isNumeric(args[0])) {
            invalidInput("notNumeric");
//
            return 0;
        }

            int fileNumber = Integer.parseInt(args[0]);

        // Negative file number
        if (fileNumber < 0) {
            invalidInput("negativeNumber");
//
            return 0;
        }

        // One argument --> display file contents
        if (args.length == 1) {
//            return Instruction.DISPLAY_FILE;
            return 1;
        }

        // Two arguments --> display file using alternate cipher key
//
        if (args.length == 2) {
            Cipher cipher = new Cipher();
            cipher.loadKey(args[1]);
            return 1;
        }
        return 0;
        }


    // Checks if a string is numeric
    private boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Prints error messages
    private void invalidInput(String type) {
        System.out.print("Invalid input: ");

        if (type.equals("notNumeric")) {
            System.out.println("file number needs to be numeric");
        } else if (type.equals("manyInputs")) {
            System.out.println("too many inputs");
        } else if (type.equals("negativeNumber")) {
            System.out.println("file number cannot be negative");
        } else {
            System.out.println("unknown error");
        }
    }
}

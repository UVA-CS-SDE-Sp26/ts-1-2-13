import java.util.Arrays;
import java.util.Objects;

public class CommandLineReader {

    public void read(String[] args) {
        // No arguments --> list available files
        FileHandler fileHandler = new FileHandler();
        if (Objects.equals(args[0], "null")) {
//            fileHandler.getFileList();
//            return Instruction.LIST_FILES;
        }

        // Too many arguments
        if (args.length > 2) {
            invalidInput("manyInputs");
//            return Instruction.ERROR;
        }

        // Empty argument
        if (args[0] == null || args[0].isEmpty()) {
            invalidInput("notNumeric");
//            return Instruction.ERROR;
        }

        // Non-numeric file number
        if (!isNumeric(args[0])) {
            invalidInput("notNumeric");
//            return Instruction.ERROR;
            return;
        }

            int fileNumber = Integer.parseInt(args[0]);

        // Negative file number
        if (fileNumber < 0) {
            invalidInput("negativeNumber");
//            return Instruction.ERROR;
        }

        // One argument --> display file contents
        if (args.length == 1) {
//            return Instruction.DISPLAY_FILE;
        }

        // Two arguments --> display file using alternate cipher key
//        return Instruction.DISPLAY_FILE_ALT_KEY;
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

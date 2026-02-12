import java.util.Scanner;
public class ProgramControl {

    public ProgramControl() throws Exception {

        //Creates instances of all classes
        Scanner sc= new Scanner(System.in);
        CommandLineReader commandLineReader = new CommandLineReader();
        FileHandler fileHandler = new FileHandler();
        Cipher cipher = new Cipher();

        //Requests a file name and an alternative cipher key
        System.out.println("Insert file request here: ");
        String fileRequest = sc.nextLine();
        System.out.println("Insert alternative cipher key filename, to use default key press enter");
        String alternativeCipherKey = sc.nextLine();

        //Creates String Array of file name and key (leaves empty if no file name present)
        String[] argsArray = {};
        if (alternativeCipherKey.isEmpty()) {
            argsArray = new String[]{fileRequest};
        }
        if (fileRequest.isEmpty()) {
                argsArray = new String[]{"null"};
            }
        if(!fileRequest.isEmpty() && !alternativeCipherKey.isEmpty()){
            argsArray = new String[]{fileRequest,alternativeCipherKey};
        }
        if (!fileRequest.isEmpty() && alternativeCipherKey.isEmpty()){
            argsArray = new String[]{fileRequest};
        }

        //verifies that the Array of arguments is valid
        int intCheck = commandLineReader.read(argsArray);

        //Runs when only file number is provided
        if (intCheck == 1) {
//            fileHandler.readFile(argsArray[0]);
        }





    }
}

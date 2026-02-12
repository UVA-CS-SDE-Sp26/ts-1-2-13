import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Cipher {
    private char[] actualChars; //what the characters decode to
    private char[] cipherChars; //how characters appear in file

    //default constructor
    public Cipher() throws Exception {
        loadKey("ciphers/key.txt");
    }

    //constructor for alternate key
    public void newCipher(String keyPath)  throws Exception {
        loadKey(keyPath);
    }

    //loads the key file and validates
    private void loadKey(String path) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(path));

        if (lines.size() < 2) {
            throw new IllegalArgumentException("Key file needs two lines");
        }

        //clean two key lines
        String actualLine = lines.get(0).trim();
        String cipherLine = lines.get(1).trim();

        if (actualLine.isEmpty() || cipherLine.isEmpty()) {
            throw new IllegalArgumentException("Key file cannot be empty");
        }

        if (actualLine.length() != cipherLine.length()) {
            throw new IllegalArgumentException("Key file lengths do not match");
        }

        //convert lines into character arrays
        actualChars = actualLine.toCharArray();
        cipherChars = cipherLine.toCharArray();

        validateKey();
    }

    //makes sure no duplicates
    private void validateKey() {
        noDuplicates(actualChars);
        noDuplicates(cipherChars);
    }
    //checks for duplicates
    private void noDuplicates(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    throw new IllegalArgumentException("Duplicate characters in key");
                }
            }
        }
    }

    //deciphers the ciphered string and returns the decoded text
    public String decipher(String cipherText) {
        String result = "";

        //go through each character in ciphered input
        for (char ch: cipherText.toCharArray()) {
            boolean found = false;
            //search for the character in the cipher array
            for (int i = 0; i < cipherChars.length; i++) {
                if (ch == cipherChars[i]) {
                    //replace cipher character with the actual character
                    result += actualChars[i];
                    found = true;
                    break;
                }
            }
            //if character is not part of the key, do not change it
            if (!found) {
                result += ch;
            }
        }
        return result;
    }

}

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {

    @TempDir
    Path tempDir;

    // Helper: create a key file with exactly two lines
    private String makeKey(String actual, String cipher, String filename) throws Exception {
        Path key = tempDir.resolve(filename);
        Files.writeString(key, actual + System.lineSeparator() + cipher + System.lineSeparator());
        return key.toString();
    }

    // Helper: create a key file with custom raw contents (for 1-line / empty-line cases)
    private String makeRawKey(String rawContents, String filename) throws Exception {
        Path key = tempDir.resolve(filename);
        Files.writeString(key, rawContents);
        return key.toString();
    }

   /*** cipher */

    // - loading a valid key file initializes the cipher correctly
    @Test
    public void constructor_validKey_initializesCipher() throws Exception {
        String key = makeKey("abc", "bca", "key1.txt");
        Cipher cipher = new Cipher(key);

        // If key maps: b->a, c->b, a->c then "bca" should become "abc"
        assertEquals("abc", cipher.decipher("bca"));
    }

    // - using an alternate key path makes different deciphered output
    @Test
    public void constructor_alternateKeyPath_changesOutput() throws Exception {
        String key1 = makeKey("abc", "bca", "keyA.txt");
        Cipher c1 = new Cipher(key1);

        String key2 = makeKey("abc", "cab", "keyB.txt");
        Cipher c2 = new Cipher(key2);

        assertNotEquals(c1.decipher("abc"), c2.decipher("abc"));
    }

    // - missing or unreadable key file results in an error
    @Test
    public void constructor_missingKey_throwsError() {
        assertThrows(IllegalArgumentException.class, () -> new Cipher("no_such_file.txt"));
    }

    /*** loadKey */
    // - loading a key file with fewer than two lines throws an error
    @Test
    public void loadKey_fewerThanTwoLines_throwsError() throws Exception {
        String raw = "abc" + System.lineSeparator(); // only one line
        String key = makeRawKey(raw, "oneLine.txt");

        assertThrows(IllegalArgumentException.class, () -> new Cipher(key));
    }

    // - loading a key file with empty lines throws an error
    @Test
    public void loadKey_emptyLine_throwsError() throws Exception {
        String key = makeKey("abc", "", "emptySecondLine.txt");
        assertThrows(IllegalArgumentException.class, () -> new Cipher(key));
    }

    // - loading a key file with different length lines throws an error
    @Test
    public void loadKey_differentLengthLines_throwsError() throws Exception {
        String key = makeKey("abcd", "xyz", "mismatch.txt");
        assertThrows(IllegalArgumentException.class, () -> new Cipher(key));
    }

    // - loading a valid key makes the character arrays
    @Test
    public void loadKey_validKey_allowsDeciphering() throws Exception {
        String key = makeKey("abc", "bca", "validArrays.txt");
        Cipher cipher = new Cipher(key);

        // If arrays were set, decipher should map correctly
        assertEquals("cab", cipher.decipher("abc"));
    }

    /*** decipher */
    // - deciphering a known ciphered string returns the correct actual text
    @Test
    public void decipher_knownCipher_returnsCorrectText() throws Exception {
        String key = makeKey("abc", "bca", "known.txt");
        Cipher cipher = new Cipher(key);

        assertEquals("abc", cipher.decipher("bca"));
    }

    // - characters not present in the cipher key remain unchanged
    @Test
    public void decipher_charsNotInKey_unchanged() throws Exception {
        String key = makeKey("abc", "bca", "punct.txt");
        Cipher cipher = new Cipher(key);

        // b->a, c->b, '!' ' ' '?' unchanged
        assertEquals("a! b?", cipher.decipher("b! c?"));
    }

    // - deciphering an empty string returns an empty string
    @Test
    public void decipher_emptyString_returnsEmpty() throws Exception {
        String key = makeKey("abc", "bca", "empty.txt");
        Cipher cipher = new Cipher(key);

        assertEquals("", cipher.decipher(""));
    }

    // - deciphering using a different key produces different output
    @Test
    public void decipher_differentKey_producesDifferentOutput() throws Exception {
        String key1 = makeKey("abc", "bca", "k1.txt");
        Cipher c1 = new Cipher(key1);

        String key2 = makeKey("abc", "cab", "k2.txt");
        Cipher c2 = new Cipher(key2);

        assertNotEquals(c1.decipher("bca"), c2.decipher("bca"));
    }
}

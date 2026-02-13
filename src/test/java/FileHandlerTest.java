import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    @TempDir
    File tempDir;

    @Test
    void fileListWithEmptyDirectory() {
        FileHandler fileHandler = new FileHandler(tempDir);
        assertNull(fileHandler.getFileList());
    }

    @Test
    void fileContentCheck() throws IOException {
        File messageFile = new File(tempDir, "message.txt");
        try (FileWriter writer = new FileWriter(messageFile)) {
            writer.write("hello\nworld");
        }
        FileHandler fileHandler = new FileHandler(tempDir);

        assertEquals("helloworld", fileHandler.readFile("message.txt"));
    }

    @Test
    void fileFindingByName() throws IOException {
        FileHandler fileHandler = new FileHandler(tempDir);

        assertEquals("File Location Not Found", fileHandler.readFile("missing.txt"));
    }

    @Test
    void invalidIndex() throws IOException {
        File singleFile = new File(tempDir, "single.txt");
        try (FileWriter writer = new FileWriter(singleFile)) {
            writer.write("content");
        }
        FileHandler fileHandler = new FileHandler(tempDir);

        assertEquals("File Index is Invalid", fileHandler.readFile(0));
    }

}

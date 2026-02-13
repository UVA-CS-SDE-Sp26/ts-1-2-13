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
        assertTrue(fileHandler.getFileList().isEmpty());
    }

    @Test
    void fileContentCheck() throws IOException {
        File messageFile = new File(tempDir, "message.txt");
        try (FileWriter writer = new FileWriter(messageFile)) {
            writer.write("hello\nworld");
        }
        FileHandler fileHandler = new FileHandler(tempDir);

        assertEquals("hello\nworld", fileHandler.readFile(1));
    }

    @Test
    void fileNotFoundByInvalidIndex() throws IOException {
        FileHandler fileHandler = new FileHandler(tempDir);

        assertEquals("File Number not Found", fileHandler.readFile(1));
    }

    @Test
    void invalidIndex() throws IOException {
        File singleFile = new File(tempDir, "single.txt");
        try (FileWriter writer = new FileWriter(singleFile)) {
            writer.write("content");
        }
        FileHandler fileHandler = new FileHandler(tempDir);

        assertEquals("File Number not Found", fileHandler.readFile(0));
    }

}

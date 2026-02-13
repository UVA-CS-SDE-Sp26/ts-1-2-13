import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    @TempDir
    Path tempDir;

    @Test
    void fileListWithEmptyDirectory() {
        FileHandler fileHandler = new FileHandler(tempDir.toFile());
        assertNull(fileHandler.getFileList());
    }

    @Test
    void fileContentCheck() throws IOException {
        Files.writeString(tempDir.resolve("message.txt"), "hello\nworld");
        FileHandler fileHandler = new FileHandler(tempDir.toFile());

        assertEquals("helloworld", fileHandler.readFile("message.txt"));
    }

    @Test
    void fileFindingByName() throws IOException {
        FileHandler fileHandler = new FileHandler(tempDir.toFile());

        assertEquals("File Location Not Found", fileHandler.readFile("missing.txt"));
    }

    @Test
    void invalidIndex() throws IOException {
        Files.writeString(tempDir.resolve("single.txt"), "content");
        FileHandler fileHandler = new FileHandler(tempDir.toFile());

        assertEquals("File Index is Invalid", fileHandler.readFile(0));
    }

}

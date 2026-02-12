import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Scanner;

public class ProgramControlTest {
    private final Scanner scanner;

    public ProgramControlTest(Scanner scanner) {
        this.scanner = scanner;
    }

    public ProgramControlTest() {
        this(new Scanner(System.in));
    }



    @BeforeEach
    void setUp() {

    }

}

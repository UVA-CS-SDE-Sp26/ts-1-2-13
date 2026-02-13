import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TopSecretTest {

    @Test
    void noArgsRunsWithoutCrashing() {
        assertDoesNotThrow(() -> TopSecret.main(new String[]{}));
    }

    @Test
    void oneArgRunsWithoutCrashing() {
        assertDoesNotThrow(() -> TopSecret.main(new String[]{"1"}));
    }
}

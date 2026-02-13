import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Errors to Test for
- Too many arguments supplied
- File number is not numeric
- Empty file number argument
- Negative File number
 */

class CommandLineReaderTest {
    @Test
    public void tooManyArguments() throws Exception {
        CommandLineReader clr = new CommandLineReader();
        String[] args = {"10", "key", "extra"};

        assertEquals(0, clr.read(args));
    }

    @Test
    public void nonNumericFileNumber() throws Exception {
        CommandLineReader clr = new CommandLineReader();
        String[] args = {"abc"};

        assertEquals(0, clr.read(args));
    }

    @Test
    public void emptyFileNumberArgument() throws Exception {
        CommandLineReader clr = new CommandLineReader();
        String[] args = {""};

        assertEquals(0, clr.read(args));
    }
    @Test
    public void negativeFileNumber() throws Exception {
        CommandLineReader clr = new CommandLineReader();
        String[] args = {"-5"};

        assertEquals(0, clr.read(args));
    }

    @Test
    public void validSingleArgument() throws Exception {
        CommandLineReader clr = new CommandLineReader();
        String[] args = {"10"};

        assertEquals(1, clr.read(args));
    }



}
import java.util.ArrayList;

public class TopSecret {
    public static void main(String[] args) throws Exception {
        try {
            ProgramControl program = new ProgramControl();
            // if ProgramControl does work in its constructor, this will catch it
            // if it has a method later, call it here
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

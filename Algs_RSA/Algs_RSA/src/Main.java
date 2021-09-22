
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        boolean evaluate;
        int value = 0;


        do {
            evaluate = (value == 0) ? Communication.Channel("Alice", "Bob", "A") : Communication.Channel("Bob", "Alice", "B");
            value = (value + 1) % 2;

        } while (!evaluate);
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * https://adventofcode.com/2020/day/2
 */
public class Prog20202 {

    public static void main(String args[])
        throws IOException {

        String[] input = Files.readString(Path.of(args[0])).split("\n");
        int count = 0;
        for (int i = 0; i < input.length; i++) {

            String[] tokens = input[i].split(": ");

            String password = tokens[1];
            char ch = tokens[0].split(" ")[1].charAt(0);
            String[] range = tokens[0].split(" ")[0].split("-");
            int min = Integer.parseInt(range[0]) - 1;
            int max = Integer.parseInt(range[1]) - 1;

            try {
                if (password.charAt(min) == ch) {
                    count++;
                    if (password.charAt(max) == ch) {
                        count--;
                    }
                }
                else if (password.charAt(max) == ch) {
                    count++;
                    if (password.charAt(min) == ch) {
                        count--;
                    }
                }
            }
            catch (final Exception e) {
                System.out.println("Exception==" + input[i]);

            }
        }
        System.out.println("Count = " + count);

    }
}

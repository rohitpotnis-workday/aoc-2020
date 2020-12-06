import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2020/day/6
 *
 * @author rohitpotnis-workday
 */
public class Prog20206 {

    public static void main(String args[])
        throws IOException {
        String path = "/Users/rohit.potnis/Downloads/20206";


        long valid = Arrays.stream(Files.readString(Path.of(path))
            .split("\n\n"))
            .mapToInt(pass -> {
                int[] setAlphs = new int[26];
                String passArr[] = pass.split("\n");
                Arrays.stream(passArr).forEach(item -> {
                    for (int i = 0; i < item.length(); i++) {
                        setAlphs[item.charAt(i)-'a'] = 1;
                    }
                });
                return Arrays.stream(setAlphs).filter(item -> item == 1).sum();
            }).sum();

        System.out.println("Part A: " + valid);

        valid = Arrays.stream(Files.readString(Path.of(path))
            .split("\n\n"))
            .mapToInt(pass -> {
                int[] setAlphs = new int[26];
                String passArr[] = pass.split("\n");
                Arrays.fill(setAlphs, 1);
                Arrays.stream(passArr).forEach(item -> {
                    int[] currAlphs = new int[26];
                    for (int i = 0; i < item.length(); i++) {
                        currAlphs[item.charAt(i)-'a'] = 1;
                    }
                    for (int i = 0; i < currAlphs.length; i++) {
                        setAlphs[i] &= currAlphs[i];
                    }
                });
                return (int)Arrays.stream(setAlphs).filter(item -> item == 1).count();
            }).sum();
        System.out.println("Part B: " + valid);

    }
}
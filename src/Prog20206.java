import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
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
                Arrays.stream(pass.split("\n")).forEach(item -> {
                    Arrays.stream(item.split("(?!^)")).forEach(ch -> setAlphs[ch.charAt(0) - 'a'] = 1);
                });
                return Arrays.stream(setAlphs).filter(item -> item == 1).sum();
            }).sum();

        System.out.println("Part A: " + valid);

        valid = Arrays.stream(Files.readString(Path.of(path))
            .split("\n\n"))
            .mapToInt(pass -> {
                int[] setAlphs = new int[26];
                Arrays.fill(setAlphs, 1);
                Arrays.stream(pass.split("\n")).forEach(item -> {
                    int[] currAlphs = new int[26];
                    int[] index = new int[1];
                    Arrays.stream(item.split("(?!^)")).forEach(ch -> currAlphs[ch.charAt(0) - 'a'] = 1);
                    Arrays.stream(currAlphs).forEach(ch -> setAlphs[index[0]++] &= ch);
                });
                return (int) Arrays.stream(setAlphs).filter(item -> item == 1).count();
            }).sum();
        System.out.println("Part B: " + valid);

    }
}
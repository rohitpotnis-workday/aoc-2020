import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://adventofcode.com/2020/day/9
 *
 * @author rohitpotnis-workday
 */
public class Prog20209 {
    public static void main(String args[])
        throws IOException {
        String path = "/Users/rohit.potnis/Downloads/20209-sample";
        long[] arr = Arrays.stream(Files.readString(Path.of(path)).split("\n")).mapToLong(Long::parseLong).toArray();
        int preamble = 5;

        for (int i = preamble; i < arr.length; i++) {
            long[] pair = find2Sum(arr[i], Arrays.copyOfRange(arr, Math.max(i - preamble, 0), i));

            if (pair == null) {
                System.out.println("Part A:" + arr[i]);
                long[] contig = findContinguousPair(0, arr[i], arr);
                Arrays.sort(contig);
                System.out.println("Part B: " + (contig[0] + contig[contig.length - 1]));
            }
        }
    }

    private static long[] findContinguousPair(int startIndex, final long sum, final long[] arr) {
        long lSum = 0;
        for (int i = startIndex; i < arr.length; i++) {
            lSum += arr[i];
            if (lSum == sum) {
                return Arrays.copyOfRange(arr, startIndex, i);
            }
            if (lSum > sum) {
                return findContinguousPair(startIndex + 1, sum, arr);
            }
        }
        return null;
    }

    private static long[] find2Sum(long sum, long[] arr) {
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                return new long[] { arr[i], map.get(arr[i]) };
            }
            map.put(sum - arr[i], arr[i]);
        }
        return null;

    }

}
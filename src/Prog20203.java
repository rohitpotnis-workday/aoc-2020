import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://adventofcode.com/2020/leaderboard/day/3
 * @author rohitpotnis-workday
 */
public class Prog20203 {

    static String DELIM_NEWLINE = "\n";
    static String DELIM_COMMA = ",";

    public static void main(String args[])
        throws IOException {

        String[] slopes = new String[] { "1,1", "3,1", "5,1", "7,1", "1,2" };
        Path path = Path.of("/Users/rohit.potnis/Downloads/input_20203.txt");
        String inputStr = Files.readString(path);

        int rowLength = inputStr.indexOf("\n");

        long result = Arrays.stream(slopes).mapToLong(slope -> {
            int shift = Integer.parseInt(slope.split(",")[0]);
            int jump = Integer.parseInt(slope.split(",")[1]);
            AtomicInteger index = new AtomicInteger();
            AtomicInteger currSteap = new AtomicInteger(jump - 1);
            int sum = (Arrays.stream(inputStr.split("\n")).mapToInt(str -> {
                int loopSum = 0;
                if (currSteap.get() > 0 && jump > 1 && currSteap.get() % (jump) == 0) {
                    loopSum = 0;
                }
                else {
                    loopSum = str.charAt(index.getAndSet((index.get() + shift) % rowLength)) == '#' ? 1 : 0;
                }
                currSteap.incrementAndGet();
                return loopSum;
            }).sum());
            return sum;
        }).reduce(Math::multiplyExact).getAsLong();

        System.out.println(result);

    }
}



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://adventofcode.com/2020/leaderboard/day/3
 *
 * @author rohitpotnis-workday
 */
public class Prog20203 {

    public static void main(String args[])
        throws IOException {

        Path path = Path.of("/Users/rohit.potnis/Downloads/input_20203.txt");
        String inputStr = Files.readString(path);

        long result = Arrays.stream("1-1,3-1,5-1,7-1,1-2".split(",")).mapToLong(slope -> {
            int[] xy = Arrays.stream(slope.split("-")).mapToInt(Integer::parseInt).toArray();
            AtomicInteger index = new AtomicInteger();
            AtomicInteger currStep = new AtomicInteger(xy[1] - 1);
            return (Arrays.stream(inputStr.split("\n")).mapToInt(str -> (currStep.get() > 0 && xy[1] > 1 && currStep.getAndIncrement() % xy[1] == 0) ? 0 : str.charAt(index.getAndSet((index.get() + xy[0]) % inputStr.indexOf("\n"))) == '#' ? 1 : 0).sum());
        }).reduce(Math::multiplyExact).getAsLong();

        System.out.println(result);

    }
}



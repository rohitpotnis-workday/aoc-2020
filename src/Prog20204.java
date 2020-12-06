import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2020/day/4
 *
 * @author rohitpotnis-workday
 */
public class Prog20204 {

    public static void main(String args[])
        throws IOException {
        String path = "/Users/rohit.potnis/Downloads/20204_input.txt";

        long valid = Arrays.stream(Files.readString(Path.of(path)).split("\n\n+"))
            .mapToInt(pass ->
                Arrays.stream(pass.replaceAll("\n", " ").split(" "))
                    .filter(kv -> kv.split(":")[0].matches("^byr|iyr|eyr|hgt|hcl|ecl|pid|cid$")) //check only valid keys
                    .mapToInt(kv -> kv.split(":")[0].equals("cid") ? 0 : 1).sum() == 7 ? 1 : 0)
            .sum();

        System.out.println("Part A: " + valid);

        valid = Arrays.stream(Files.readString(Path.of(path)).split("\n\n+"))
            .mapToInt(pass ->
                Arrays.stream(pass.replaceAll("\n", " ").split(" "))
                    .filter(kv -> kv.split(":")[0].matches("^byr|iyr|eyr|hgt|hcl|ecl|pid|cid$")) //check only valid keys
                    .filter(kv -> (kv.matches("(^(byr:19[2-9][0-9])$|^(byr:200[0-2])$)|(^(iyr:20(1[0-9]|20))$)|(^(eyr:20(2[0-9]|30))$)|(^hgt:((1([5-8][0-9]|9[0-3])cm)|(59|6[0-9]|7[0-6])in)$)|(^hcl:#[0-9,a-f]{6}$)|(^ecl:(amb|blu|brn|gry|grn|hzl|oth)$)|(^pid:[0-9]{9}$)")))
                    .mapToInt(kv -> kv.split(":")[0].equals("cid") ? 0 : 1).sum() == 7 ? 1 : 0)
            .sum();
        System.out.println("Part B: " + valid);

    }
}
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2020/leaderboard/day/4
 *
 * @author rohitpotnis-workday
 */
public class Prog20204 {

    public static void main(String args[])
        throws IOException {
        Path path = Path.of("/Users/rohit.potnis/Downloads/northpole.txt");
        long valid = Arrays.stream(Files.readString(path).split("\n\n+"))
            .filter(passport -> {
                //passport entry
                Set<String> fields = Arrays.stream(passport.replace("\n", " ").split(" "))
                    .filter(keyval -> !keyval.contains("byr:") || keyval.matches("^(byr:19[2-9][0-9])$|^(byr:200[0-2])$"))
                    .filter(keyval -> !keyval.contains("iyr:") || keyval.matches("^(iyr:20(1[0-9]|20))$"))
                    .filter(keyval -> !keyval.contains("eyr:") || keyval.matches("^(eyr:20(2[0-9]|30))$"))
                    .filter(keyval -> !keyval.contains("hgt:") || keyval.matches("hgt:((1([5-8][0-9]|9[0-3])cm)|(59|6[0-9]|7[0-6])in)"))
                    .filter(keyval -> !keyval.contains("hcl:") || keyval.matches("hcl:#[0-9,a-f]{6}"))
                    .filter(keyval -> !keyval.contains("ecl:") || keyval.matches("ecl:(amb|blu|brn|gry|grn|hzl|oth)"))
                    .filter(keyval -> !keyval.contains("pid:") || keyval.matches("pid:[0-9]{9}"))
                    .collect(Collectors.toSet());
                return fields.size() == 8 || (fields.size() == 7 && fields.stream().filter(item->item.contains("cid")).count()==0);
            }).count();
        System.out.println(valid);

    }
}
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2020/day/7
 *
 * @author rohitpotnis-workday
 */
public class Prog20207 {

    private static final String BAG_COLOR = "shiny gold";

    public static void main(String args[])
        throws IOException {
        String path = "/Users/rohit.potnis/Downloads/20207";
        Map<String, Bag> bags = new HashMap<>();
        Arrays.stream(Files.readString(Path.of(path)).split("\n"))
            .forEach(rule -> {
                String[] tokens = rule.split(" bags contain ");
                String color = tokens[0];
                Set<String> containStr = Arrays.stream(tokens[1].split("bag[s]*[,|.]")).map(str -> str.trim()).collect(Collectors.toSet());
                bags.putIfAbsent(color, new Bag(color));
                Bag topBag = bags.get(color);
                containStr.stream().filter(str -> !str.equalsIgnoreCase("no other"))
                    .collect(Collectors.toSet()).forEach(colorQty -> {
                    String[] cqTokens = colorQty.split(" ");
                    String cqColor = Arrays.stream(Arrays.copyOfRange(cqTokens, 1, cqTokens.length)).collect(Collectors.joining(" "));
                    int cqQty = Integer.parseInt(cqTokens[0]);
                    bags.putIfAbsent(cqColor, new Bag(cqColor));
                    topBag.containedBags.putIfAbsent(bags.get(cqColor), cqQty);
                });
            });

        bags.values().stream().forEach(Bag::prepareFlatMap);

        long count = bags.values().stream().filter(bag -> !bag.color.equalsIgnoreCase("other") && bag.contains(bags.get(BAG_COLOR))).count();
        System.out.println("Part A: " + count);

        count = bags.get(BAG_COLOR).getNumSubBags();
        System.out.println("Part B: " + count);
    }

    private static class Bag {

        boolean prepared = false;
        String color;
        Map<Bag, Integer> containedBags = new HashMap<>();
        Set<Bag> flatSet = new HashSet<>();

        public Bag(String color) {
            this.color = color;
        }

        public int getNumSubBags() {
            return this.containedBags.keySet().stream().mapToInt(bag -> this.containedBags.get(bag) * (1 + bag.getNumSubBags())).sum();
        }

        public boolean contains(Bag bag) {
            return this.flatSet.contains(bag);
        }

        public void prepareFlatMap() {
            if (prepared) {
                return;
            }
            this.containedBags.keySet().forEach(bag -> {
                bag.prepareFlatMap();
                this.flatSet.add(bag);
                this.flatSet.addAll(bag.flatSet);
            });
            prepared = true;
        }
    }

}
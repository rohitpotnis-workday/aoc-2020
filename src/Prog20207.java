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
        Arrays.stream(Files.readString(Path.of(path))
            .split("\n"))
            .forEach(rule -> {

                String[] tokens = rule.split(" bags contain ");
                String color = tokens[0];
                String[] containStr = tokens[1].split("bag[s]*[,|.]");
                containStr = Arrays.stream(containStr).map(str -> str.trim()).toArray(String[]::new);
                if (!bags.containsKey(color)) {
                    bags.put(color, new Bag(color));
                }
                Bag topBag = bags.get(color);

                Arrays.stream(containStr).filter(str -> !str.equalsIgnoreCase("no other")).collect(Collectors.toSet()).forEach(colorQty -> {
                    String[] cqTokens = colorQty.split(" ");
                    String cqColor = Arrays.stream(Arrays.copyOfRange(cqTokens, 1, cqTokens.length)).collect(Collectors.joining(" "));
                    if (!bags.containsKey(cqColor)) {
                        bags.put(cqColor, new Bag(cqColor));
                    }
                    int cqQty = Integer.parseInt(cqTokens[0]);
                    if (cqQty > 0 && !topBag.containedBags.containsKey(cqColor)) {
                        topBag.add(bags.get(cqColor), cqQty);
                    }
                });
                return;
            });
        bags.values().stream().forEach(bag -> bag.prepareFlatMap());

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

        public void add(Bag bag, int qty) {
            this.containedBags.put(bag, qty);
            this.flatSet.add(bag);
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
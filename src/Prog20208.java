import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://adventofcode.com/2020/day/8
 *
 * @author rohitpotnis-workday
 */
public class Prog20208 {

    public static void main(String args[])
        throws IOException {
        String path = "/Users/rohit.potnis/Downloads/20208";
        String[] instSet = Files.readString(Path.of(path)).split("\n");
        int currStep = 0;
        for (int i = 0; i < instSet.length; i++) {
            String instStr = instSet[i];
            String inst = instStr.substring(0, 3);
            String swapInst = inst.equals("jmp")?"nop":"jmp";

            if (i > currStep && (inst.contains("jmp") || inst.contains("nop"))) {
                currStep = i;
                try {
                    instSet[currStep] = instStr.replace(inst, swapInst);
                    int accumulator = process(instSet);
                    System.out.println("Accumulator = " + accumulator);
                    break;
                }
                catch (final Exception e) {
                    instSet[currStep] = instStr.replace(swapInst, inst);
                }

            }
        }
        System.exit(1);
    }

    private static int process(final String[] instSet)
        throws Exception {
        int accumulator = 0;

        final Map<Integer, String> instLoc = new HashMap<>();

        for (int index = 0; index < instSet.length; index++) {
            String instStr = instSet[index];
            String inst = instStr.substring(0, 3);
            String op = instStr.substring(4, 5);
            int opnd = Integer.parseInt(instStr.substring(5));

            if (instLoc.containsKey(index)) {
                throw new Exception("failed");
            }
            instLoc.put(index, instStr);

            switch (inst) {
            case "nop": {
                break;
            }
            case "acc": {
                accumulator = op.equalsIgnoreCase("+") ? accumulator + opnd : accumulator - opnd;
                break;
            }
            case "jmp": {
                index = op.equalsIgnoreCase("+") ? index + opnd : index - opnd;
                index--;
            }
            break;
            }
        }
        return accumulator;
    }

}
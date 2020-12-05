import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2020/leaderboard/day/4
 *
 * @author rohitpotnis-workday
 */
public class Prog20205 {

    public static void main(String args[])
        throws IOException {

        Path path = Path.of("/Users/rohit.potnis/Downloads/20205_input");
        int maxSeatId = 0;


        int [] seatassigned = new int[8*128];
        Arrays.fill(seatassigned,0);
        String[] passes = Files.readString(path).split("\n");

        for (String pass: passes){
            int row = 0;
            int lower = 0;
            int higher= (int) (Math.pow(2,7)-1);
            for (int i=0; i<pass.length()-3; i++){
                switch(pass.charAt(i)) {
                case 'B':{
                    lower += (higher-lower)/2+1;
                    row = lower;
                    break;
                }
                case 'F': {
                    higher -= (higher-lower)/2+1;
                    row = higher;
                }

                }
            }
            int l = 0;
            int r = 7;
            int c = 0;
            for (int i=3; i> 0; i--){
                switch(pass.charAt(pass.length()-i)) {
                case 'R':{
                    l += (r-l)/2+1;
                    c=l;
                    break;
                }
                case 'L': {
                    r -= (r-l)/2+1;
                    c=r;
                }

                }

            }
            int seatId =  lower*8+l;
            maxSeatId = Math.max(maxSeatId,seatId);
            seatassigned[seatId]=1;

            for (int i=0; i<seatassigned.length; i++){
//                System.out.println(seatassigned[i]);
            }



        }
        for (int i=1; i<8*128-1; i++){
            if (seatassigned[i-1] + seatassigned[i+1]==2 && seatassigned[i]==0){
                System.out.println(i);
            }
        }
//        System.out.println(maxSeatId);


//        Arrays.stream(Files.readString(path).split("\n")).mapToInt(item->item.chars().forEach(
//            ch->{
//                int row = 2^7-1;
//                while (ch!='L' && ch!='R'){
//                    row = ch=='F'?row/2:row;
//                }
//            }));
//        System.out.println(row);
    }
}
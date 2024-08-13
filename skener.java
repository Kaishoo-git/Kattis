import java.io.*;
import java.util.*;

public class skener {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] in = br.readLine().split(" ");
        int r = Integer.parseInt(in[0]); int c = Integer.parseInt(in[1]);
        int zr = Integer.parseInt(in[2]); int zc = Integer.parseInt(in[3]);
        for (int i = 0; i < r; i++) {
            String[] line = br.readLine().split("");
            String out = "";
            for (String s:line) {
                for (int k = 0; k<zc; k++) {out += s;}
            }
            for (int k = 0; k<zr;k++){pw.println(out);}
        }
        pw.close();
    }
}

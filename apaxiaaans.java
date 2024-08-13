import java.util.*;
import java.io.*;

public class apaxiaaans {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] s = br.readLine().split("");
        String t = s[0];
        for (int i = 1; i < s.length; i++) {
            if (!s[i].equals(s[i-1])) {
                t += s[i];
            }
        }
        pw.println(t);
        pw.close();
    }
}

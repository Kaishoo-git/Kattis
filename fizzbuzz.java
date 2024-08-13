import java.util.*;
import java.io.*;

public class fizzbuzz {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] in = br.readLine().split(" ");
        int x = Integer.parseInt(in[0]); int y = Integer.parseInt(in[1]); int n = Integer.parseInt(in[2]);
        for (int i = 1; i <= n; i++) {
            if (i%x==0&&i%y==0){
                pw.println("FizzBuzz");
            } else if (i%x == 0) {
                pw.println("Fizz");
            } else if (i%y==0) {
                pw.println("Buzz");
            } else {
                pw.println(i);
            }
        }
        pw.close();
    }
}

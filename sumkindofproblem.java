import java.util.*;
import java.io.*;

public class sumkindofproblem {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(System.out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[1]);
            int c1 = 1; int c2 = 1; int c3 = 1; int s1 = 0; int s2 = 0; int s3 = 0;
            int x = 1;
            while (c1 <= n) {
                s1 += x;
                c1++; x++;
            }
            x=1;
            while (c2 <= n) {
                if (x%2==1){s2+=x;c2++;}
                x++;
            }
            x=1;
            while (c3 <= n) {
                if (x%2==0){s3+=x;c3++;}
                x++;
            }
            pw.println(String.format("%d %d %d %d", i+1, s1, s2, s3));
        }
        pw.close();
    }
}

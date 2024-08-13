import java.util.*;
import java.io.*;

public class shatteredcake {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int w = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int a = 0;
        for (int i=0;i<n;i++){
            String[] in = br.readLine().split(" ");
            a += Integer.parseInt(in[0])*Integer.parseInt(in[1]);
        }
        pw.println(a/w);
        pw.close();
    }
}

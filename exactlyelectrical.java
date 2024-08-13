import java.util.*;
import java.io.*;

public class exactlyelectrical {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] in = br.readLine().split(" ");
        int sx = Integer.parseInt(in[0]); int sy = Integer.parseInt(in[1]);
        in = br.readLine().split(" ");
        int ex = Integer.parseInt(in[0]); int ey = Integer.parseInt(in[1]);
        int t = Integer.parseInt(br.readLine());
        int d = Math.abs(sx-ex) + Math.abs(sy-ey);
        if (t < d){pw.println("N"); pw.close(); return;}
        if (Math.abs(d-t)%2==1){pw.println("N"); pw.close(); return;}
        pw.println("Y"); pw.close();
    }
}

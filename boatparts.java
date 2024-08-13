import java.util.*;
import java.io.*;

public class boatparts {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] in = br.readLine().split(" ");
        int p = Integer.parseInt(in[0]); int n = Integer.parseInt(in[1]);
        HashMap<String, Integer> map = new HashMap<String, Integer>(); int c = 0; int d = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (!map.containsKey(s)) {map.put(s,0);c++;}
            map.put(s, map.get(s) + 1);
            if (map.size() == p){d = Math.min(i + 1, d);}
        }
        if (d == Integer.MAX_VALUE) {pw.println("paradox avoided");}
        else {pw.println(d);}
        pw.close(); 
    }
}

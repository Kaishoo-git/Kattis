import java.util.*;
import java.io.*;

public class grandpabernie {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            if (!map.containsKey(in[0])) {map.put(in[0], new ArrayList<Integer>());}
            map.get(in[0]).add(Integer.parseInt(in[1]));
        }
        map.forEach((string, array) -> Collections.sort(array));
        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            String[] in = br.readLine().split(" ");
            pw.println(map.get(in[0]).get(Integer.parseInt(in[1]) - 1));
        }
        pw.close();
    }
}

import java.util.*;
import java.io.*;

public class ferryloading4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[] in = br.readLine().split(" ");
            int l = Integer.parseInt(in[0]) * 100; int m = Integer.parseInt(in[1]);
            LinkedList<Integer> left = new LinkedList<Integer>();
            LinkedList<Integer> right = new LinkedList<Integer>();
            for (int j = 0; j < m; j++) {
                in = br.readLine().split(" ");
                if (in[1].equals("left")) {left.offer(Integer.parseInt(in[0]));}
                else {right.offer(Integer.parseInt(in[0]));}
            }
            String side = "left"; int trips = 0;
            while (!left.isEmpty() || !right.isEmpty()) {
                int capacity = 0;
                if (side.equals("left")) {
                    while (!left.isEmpty() && capacity + left.peek() <= l) {capacity += left.poll();}
                    side = "right";
                }
                else if (side.equals("right")) {
                    while (!right.isEmpty() && capacity + right.peek() <= l) {capacity += right.poll();}
                    side = "left";
                }
                trips++;
            }
            pw.println(trips);
        }
        pw.close();
    }
}
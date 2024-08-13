import java.util.*;
import java.io.*;

public class integerlists {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[] com = br.readLine().split("");
            br.readLine();
            String in = br.readLine();
            String data = in.substring(1, in.length() - 1);
            String[] line;
            if (!data.equals("")) {
                line = data.split(",");
            } else {
                line = new String[0];
            }
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int j = 0; j < line.length; j++) {
                ll.add(Integer.parseInt(line[j]));
            }
            boolean flag = true; int d = 1;
            for (int j = 0; j < com.length; j++) {
                if (com[j].equals("D")){
                    if (ll.isEmpty()) {flag = false; break;}
                    if (d == 1) {ll.removeFirst();}
                    else {ll.removeLast();}
                } else {
                    d = d==1 ? 2 : 1;
                }
            }
            int c = 0;
            if (flag) {
                pw.print("[");
                if (d == 1) {
                    while (!ll.isEmpty()) {
                        if (c!=0){pw.print(",");}
                        pw.print(ll.pollFirst()); c++;
                    }
                } else {
                    while (!ll.isEmpty()) {
                        if (c!=0){pw.print(",");}
                        pw.print(ll.pollLast()); c++;
                    }
                }
                pw.print("]\n");
            } else {
                pw.print("error\n");
            }
        }
        pw.close();
    }
}

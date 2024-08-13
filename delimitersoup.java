import java.util.*;
import java.io.*;

public class delimitersoup {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        Stack<String> stack = new Stack<String>(); boolean flag = true;
        int l = Integer.parseInt(br.readLine());
        String[] in = br.readLine().split("");
        for (int i = 0; i < in.length; i++) {
            if (in[i].equals(" ")){continue;}
            if (in[i].equals("(") || in[i].equals("[") || in[i].equals("{")) {
                stack.push(in[i]); // pw.println("Pushed " + in[i]);
            } else {
                if (stack.isEmpty()) {pw.println(in[i] + " " + i); flag = false; break;}
                String deli = stack.pop(); // pw.println("Popped " + deli);
                if (in[i].equals(")")) {
                    if (!deli.equals("(")) { pw.println(in[i] + " " + i); flag = false; break;}
                }
                if (in[i].equals("]")) {
                    if (!deli.equals("[")) { pw.println(in[i] + " " + i); flag = false; break;}
                }
                if (in[i].equals("}")) {
                    if (!deli.equals("{")) { pw.println(in[i] + " " + i); flag = false; break;}
                }
            }
        }
        if (flag) {pw.println("ok so far");}
        pw.close();
    }
}

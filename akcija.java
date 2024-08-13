import java.util.*;
import java.io.*;

public class akcija {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < n; i ++) {
            array.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(array);
        int c = 1; int price = 0;
        for (int i = array.size() - 1; i >= 0; i--) {
            if (c % 3 != 0) {
                price += array.get(i);
            }
            c++;
        }
        pw.println(price);
        pw.close();
    }
}

import java.util.*;
import java.io.*;

public class password {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        ArrayList<Double> array = new ArrayList<Double>();
        for (int i = 0; i < n; i++) {
            double p = Double.parseDouble(br.readLine().split(" ")[1]);
            array.add(p);
        }
        Collections.sort(array);
        double o = 0; int c = 1;
        for (int i = array.size() - 1; i >= 0; i--) {
            o += array.get(i) * c;
            c++;
        }
        pw.println(o);
        pw.close();
    }
}

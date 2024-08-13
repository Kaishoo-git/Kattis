import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class whatdoesthefoxsay {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out); 
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[] o = br.readLine().split(" ");
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            boolean flag = true;
            while (flag) {
                String[] in = br.readLine().split(" ");
                if (in[0].equals("what")) {break;}
                map.put(in[2], 1);
            }
            // map.forEach((key, value) -> System.out.println(key + " = " + value));
            Stream<String> stream = Arrays.stream(o);
            stream = stream.filter(x -> !map.containsKey(x));
            stream.forEach(x -> pw.print(x + " ")); pw.print("\n");
        }
        pw.close();
    }
}

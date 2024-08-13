import java.util.*;
import java.io.*;

public class teque2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numberOfStudents = Integer.parseInt(br.readLine());

        HashMap<Set<Integer>, Integer> hashmap = new HashMap<Set<Integer>, Integer>();
        for (int i = 1; i <= numberOfStudents; i++) {
            Set<Integer> hashset = new HashSet<Integer>();
            String[] input = br.readLine().split(" ");
            int numCourse = 4;
            for (int j = 0; i < numCourse; i++) {
                String course = input[j];
                hashset.add(Integer.parseInt(course));
            }    
            if (hashmap.containsKey(hashset)) {
                int temp = hashmap.get(hashset);
                hashmap.replace(hashset, temp + 1);
            } else {
                hashmap.put(hashset, 1);
            }
        }

        int mostPopular = 0;
        int output = 0;
        for (Map.Entry<Set<Integer>, Integer> set : hashmap.entrySet()) {
            int temp = set.getValue();
            if (temp > mostPopular) {
                output = 0;
                output = output + mostPopular;
            } else if (temp == mostPopular) {
                output = output + temp;
            }
        }
        pw.println(output);
        pw.close();
    }
}
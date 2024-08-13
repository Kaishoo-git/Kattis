import java.util.*;
import java.io.*;

public class conformity {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data = br.readLine();
        int numOfStudents = Integer.parseInt(data);

        HashMap<Set<Integer>, Integer> hashmap = new HashMap<Set<Integer>, Integer>();
        for (int i = 1; i <= numOfStudents; i++) {
            Set<Integer> hashset = new HashSet<Integer>();
            String[] input = br.readLine().split(" ");
            int numCourse = 5;
            for (int j = 0; j < numCourse; j++) {
                String course = input[j];
                hashset.add(Integer.parseInt(course));
            }    
            if (hashmap.get(hashset) != null) {
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
                mostPopular = temp;
                output = 0;
                output = output + temp;
            } else if (temp == mostPopular) {
                output = output + temp;
            }
        }
        System.out.println(output);
    }
}
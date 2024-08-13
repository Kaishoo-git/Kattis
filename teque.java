import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class teque {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int size1 = 0;
        int size4 = 0;
        
        int numOps = Integer.parseInt(br.readLine());
        int size = numOps * 2;
        ArrayList<Integer> array1 = new ArrayList<>(Collections.nCopies(size, 0));
        ArrayList<Integer> array4 = new ArrayList<>(Collections.nCopies(size, 0));
        int array1head = numOps;
        int array1tail = numOps;
        int array4head = numOps;
        int array4tail = numOps;

        for (int opNum = 1; opNum <= numOps; opNum++) {
            String[] array = br.readLine().split(" ");
            String function = array[0];
            int input = Integer.parseInt(array[1]);
            int output;

            if (function.charAt(0) == 'p') {
                if (function.charAt(5) == 'b') {
                    array4tail = array4tail + 1;
                    if (size4 == 0) {
                        array4tail = array4tail - 1;
                    }
                    array4.set(array4tail, input);
                    size4 = size4 + 1;
                    if (size4 > size1) {
                        array1tail = array1tail + 1;
                        if (size1 == 0) {
                            array1tail = array1tail - 1;
                        }
                        array1.set(array1tail, array4.get(array4head));
                        size1 = size1 + 1;
                        array4head = array4head + 1;
                        if (size4 == 1) {
                            array4tail = array4tail + 1;
                        }
                        size4 = size4 - 1;
                    }
                } else if (function.charAt(5) == 'f') {
                    array1head = array1head - 1;
                    if (size1 == 0) {
                        array1head = array1head + 1;
                    }
                    array1.set(array1head, input);
                    size1 = size1 + 1;
                    if (size1 > size4 + 1) {
                        array4head = array4head - 1;
                        if (size4 == 0) {
                            array4head = array4head + 1;
                        }
                        array4.set(array4head, array1.get(array1tail));
                        array1tail = array1tail - 1;
                        size4 = size4 + 1;
                        size1 = size1 - 1;
                    }
                } else if (function.charAt(5) == 'm') {
                    if (size1 > size4) {
                        array4head = array4head - 1;
                        if (size4 == 0) {
                            array4head = array4head + 1;
                        }
                        array4.set(array4head, input);
                        size4 = size4 + 1;
                    } else {
                        array1tail = array1tail + 1;
                        if (size1 == 0) {
                            array1tail = array1tail - 1;
                        }
                        array1.set(array1tail, input);
                        size1 = size1 + 1;
                    }
                }
            } else {
                int key = input;
                if (key >= size1) {
                    key = key - size1;
                    output = array4.get(key + array4head);
                } else {
                    output = array1.get(key + array1head);
                }
                pw.println(output);
            }
        }
        pw.close();
    }
}

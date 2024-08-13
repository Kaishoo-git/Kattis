import java.util.*;
import java.io.*;

class FirstLetterComparator implements Comparator<String> {
    @Override
    public int compare(String nameOne, String nameTwo) {
        if (nameOne.charAt(0) < nameTwo.charAt(0)) {
            return - 1;
        } else if (nameOne.charAt(0) > nameTwo.charAt(0)) {
            return 1;
        } else {
            return 0;
        }
    }
}

class SecondLetterComparator implements Comparator<String> {
    @Override
    public int compare(String nameOne, String nameTwo) {
        if (nameOne.charAt(1) < nameTwo.charAt(1)) {
            return - 1;
        } else if (nameOne.charAt(1) > nameTwo.charAt(1)) {
            return 1;
        } else {
            return 0;
        }
    }
}

public class sortofsorting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String data = br.readLine();
            int numNames = Integer.parseInt(data);
            if (numNames == 0) {
                break;
            }
            ArrayList<String> testCase = new ArrayList<String>();
            for (int i = 0; i < numNames; i++) {
                String name = br.readLine();
                testCase.add(name);
            }
            Collections.sort(testCase, new SecondLetterComparator());
            Collections.sort(testCase, new FirstLetterComparator());
            for (int i = 0; i < testCase.size(); i++) {
                System.out.println(testCase.get(i));
            }
            System.out.println();
        }
    }    
}

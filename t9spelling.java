import java.io.*;

public class t9spelling {

    static String[] keyVocab = {
            "2", "22", "222",
            "3", "33", "333",
            "4", "44", "444",
            "5", "55", "555",
            "6", "66", "666",
            "7", "77", "777", "7777",
            "8", "88", "888",
            "9", "99", "999", "9999",
            "0"
    };

    static String letterVocab = "abcdefghijklmnopqrstuvwxyz ";
    
    static String getKeyPresses(String data) {
        StringBuilder keyPresses = new StringBuilder();
        char prevKey = ' ';

        for (char c : data.toCharArray()) {
            int index = letterVocab.indexOf(c);
            String key = keyVocab[index];
            char currentKey = key.charAt(0);

            if (currentKey == prevKey) {
                keyPresses.append(" ");
            }

            keyPresses.append(key);
            prevKey = currentKey;
        }

        return keyPresses.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCases = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum <= numCases; caseNum++) {
            String data = br.readLine();
            String keyPresses = getKeyPresses(data);
            System.out.println("Case #" + caseNum + ": " + keyPresses);
        }
    }


}

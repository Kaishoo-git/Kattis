import java.io.*;
import java.util.*;

public class millionairemadness {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] data = br.readLine().split(" ");
        int rows = Integer.parseInt(data[0]);
        int cols = Integer.parseInt(data[1]);

        boolean[][] taken = new boolean[rows][cols];
        int[][] map = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                taken[i][j] = false;
            }
        }

        PriorityQueue<IntPair> pq = new PriorityQueue<IntPair>();

        pq.add(new IntPair(0, 0, 0));
        int ladder = 0;
 
        // Terminate early if endPoint == V - 1
        while (!pq.isEmpty() && !taken[rows - 1][cols - 1]) {
            IntPair u = pq.poll();
            int row = u.x, col = u.y;
            int curr = map[row][col];

            ladder = Math.max(u.weight, ladder);
            taken[row][col] = true;

            // Add all unexplored Neighbours
            // Check Right
            if (col < cols - 1) {
                int right = map[row][col + 1];
                int weight = Math.max(right - curr, 0);
                if (!taken[row][col + 1]) {pq.add(new IntPair(weight, row, col + 1)); }
            }
            // Check down
            if (row < rows - 1) {
                int down = map[row + 1][col];
                int weight = Math.max(down - curr, 0);
                if (!taken[row + 1][col]) {pq.add(new IntPair(weight, row + 1, col)); }
            } 
            // Check Left
            if (col > 0) {
                int left = map[row][col - 1];
                int weight = Math.max(left - curr, 0);
                if (!taken[row][col - 1]) {pq.add(new IntPair(weight, row, col - 1)); }
            }       
            // Check up
            if (row > 0) {
                int top = map[row - 1][col];
                int weight = Math.max(top - curr, 0);
                if (!taken[row - 1][col]) {pq.add(new IntPair(weight, row - 1, col)); }
            }
        }

        pw.write(Integer.toString(ladder));
        pw.close();
    }

}

class IntPair implements Comparable<IntPair> {
    public int weight, x, y;
    public IntPair(int w, int x1, int y1) {
        weight = w; x = x1; y = y1;
    }

    public int compareTo(IntPair o) {
        return this.weight - o.weight;
    }
}

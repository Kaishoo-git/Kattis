import java.util.*;
import java.io.*;

public class islands3 {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);

        int row = io.getInt();
        int col = io.getInt();
        int vertices = row * col;
        
        String[][] graph = new String[row][col];
        int[] visited = new int[vertices];
        for (int i = 0; i < row; i++) {
            String[] rowi = io.getWord().split("");
            for (int j = 0; j < col; j++) {
                int index = i * col + j;
                graph[i][j] = rowi[j];
                visited[index] = 0;
            }
        }

        int islandCount = 0;
        LinkedList<Pair> linkedList = new LinkedList<Pair>();
        
        // Traverse the matrix
        for (int i = 0; i < vertices; i++) {
            int rowPos = Math.floorDiv(i, col);
            int colPos = i % col;
            String island = graph[rowPos][colPos];
            if (island.equals("L") && visited[i] == 0) {
                linkedList.add(new Pair(rowPos, colPos));
                islandCount += 1;
                // DFS starts
                while (!linkedList.isEmpty()) {
                    Pair pair = linkedList.poll();
                    int rowCurr = pair.first;
                    int colCurr = pair.second;
                    int index = rowCurr * col + colCurr;
                    visited[index] = 1;
                    if (colCurr > 0) {
                        if (!graph[rowCurr][colCurr - 1].equals("W") && 
                        visited[(rowCurr) * col + colCurr - 1] == 0) {
                            linkedList.addFirst(new Pair(rowCurr, colCurr - 1));
                        }
                    }
                    if (rowCurr < row - 1) {
                        if (!graph[rowCurr + 1][colCurr].equals("W") &&
                        visited[(rowCurr + 1) * col + colCurr] == 0) {
                            linkedList.addFirst(new Pair(rowCurr + 1, colCurr));
                        }
                    }
                    if (colCurr < col - 1) {
                        if (!graph[rowCurr][colCurr + 1].equals("W") && 
                        visited[rowCurr * col + colCurr + 1] == 0) {
                            linkedList.addFirst(new Pair(rowCurr, colCurr + 1));
                        }
                    }
                    if (rowCurr > 0) {
                        if (!graph[rowCurr - 1][colCurr].equals("W") && 
                        visited[(rowCurr - 1) * col + colCurr] == 0) {
                            linkedList.addFirst(new Pair(rowCurr - 1, colCurr));
                        }
                    }
                }
            }
        }
        System.out.println(islandCount);

        io.close();
    }
}

class Pair {
    public int first, second;
    public Pair(int i, int j) {first = i; second = j;}
}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
import java.util.*;
import java.io.*;

public class MillionaireArray {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);

        int length = io.getInt();
        int width = io.getInt();
        int numOfVertices = length * width;
        ArrayList<Pair> [] adjList = new ArrayList[numOfVertices];
        int[][] graph = new int[length][width];
        
        // Test for Empty / Single Vertex

        // Map out graph
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                int tower = io.getInt();
                graph[row][col] = tower;
            }
        }

        // Create Adjacent List
        for (int index = 0; index < length * width; index++) {
            ArrayList<Pair> edgeList = new ArrayList<Pair>();
            int rowPos = Math.floorDiv(index, width);
            int colPos = index % width;
            int curr = graph[rowPos][colPos];            
            // Check right
            if (colPos < width - 1) {
                int right = graph[rowPos][colPos + 1];
                int weight = Math.max(right - curr, 0);
                int temp = rowPos * width + colPos + 1;
                edgeList.add(new Pair(weight, temp)); 
            }
            // Check down
            if (rowPos < length - 1) {
                int down = graph[rowPos + 1][colPos];
                int weight = Math.max(down - curr, 0);
                int temp = (rowPos + 1) * width + colPos;
                edgeList.add(new Pair(weight, temp));
            } 
            // Check Left
            if (colPos > 0) {
                int left = graph[rowPos][colPos - 1];
                int weight = Math.max(left - curr, 0);
                int temp = rowPos * width + colPos - 1;
                edgeList.add(new Pair(weight, temp));
            }       
            // Check up
            if (rowPos > 0) {
                int top = graph[rowPos - 1][colPos];
                int weight = Math.max(top - curr, 0);
                int temp = (rowPos - 1) * width + colPos;
                edgeList.add(new Pair(weight, temp));
            }
            adjList[index] = edgeList;
        }
        
        // Start Prim's Algorithm (Modified)
        Pair[] A = new Pair[numOfVertices];
        for (int i = 0; i < A.length; i++) {
            A[i] = new Pair(Integer.MAX_VALUE, i);
        }

        ArrayList<Boolean> taken = new ArrayList<Boolean>();
        for (int j = 0; j < numOfVertices; j++) {
            taken.add(false);
        }
        A[0].first = 0;

        for (int j = 0; j < numOfVertices; j++) {
            // Find vertices with minimum weight v
            int v = 0;
            int minLadder = Integer.MAX_VALUE;
            for (int i = 0; i < A.length; i++) {
                Pair edge = A[i];
                if (edge.first < minLadder) { 
                    minLadder = edge.first;
                    v = i;
                }
            }
            A[v].first = Integer.MAX_VALUE;
            taken.set(v, true);

            // Check all u neighbours of v
            ArrayList<Pair> edgeList = adjList[v];
            for (int i = 0; i < edgeList.size(); i++) {
                Pair edge = edgeList.get(i);
                int u = edge.second;
                if (A[u].first > edge.first && !taken.get(u)) {
                    A[u].first = edge.first;
                    A[u].second = v;
                }
            }
        }

        // MST FOUND
        int ladder = 0;
        int endPoint = numOfVertices - 1;
        while (A[endPoint].second != endPoint) {
            int parent = A[endPoint].second;
            int endRow = Math.floorDiv(endPoint, width), endCol = endPoint % width;
            int pRow = Math.floorDiv(parent, width), pCol = parent % width;
            int heightDiff = Math.max(graph[endRow][endCol] - graph[pRow][pCol], 0);
            if (heightDiff > ladder) {
                ladder = heightDiff;
            }
            endPoint = A[endPoint].second;
        }
        System.out.println(ladder);
        io.close();
    }

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

class Pair implements Comparable<Pair> {
    public int first, second;
    Pair(int x, int y) {
        first = x; second = y;
    }

    public int compareTo(Pair other) {
        if (this.first != other.first) {
            return this.first - other.first;
        } else {
            return this.second - other.second;
        }
    }
}
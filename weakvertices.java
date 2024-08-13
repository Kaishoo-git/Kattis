import java.io.*;
import java.util.*;

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


public class weakvertices {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);
        int vertices = io.getInt();

        while (vertices != - 1) {
            int[][] adjMatrix = new int[vertices][vertices];
            ArrayList<Integer> [] adjList = new ArrayList[vertices];
            String weakVertices = "";

            for (int row = 0; row < vertices; row++) {
                ArrayList<Integer> edgeList = new ArrayList<Integer>(); 
                for (int col = 0; col < vertices; col++ ) {
                    int value = io.getInt();
                    adjMatrix[row][col] = value;
                    if (value == 1) {
                        edgeList.add(col);
                    }
                }
                adjList[row] = edgeList;
            }

            for (int vertex = 0; vertex < vertices; vertex++) {
                boolean weak = true;
                ArrayList<Integer> edgeList = adjList[vertex];

                if (edgeList.size() >= 1) {
                    for (int current = 0; current < edgeList.size(); current++) {
                        int vertex1 = edgeList.get(current);
                        for (int vertex2 : edgeList) {
                            if (adjMatrix[vertex1][vertex2] == 1) {
                                weak = false;
                            }
                        }
                    }
                } 

                if (weak) {
                    if (!weakVertices.equals("")) {
                        weakVertices += " ";
                    }
                    weakVertices += vertex;
                }
            }
            io.write(weakVertices + "\n");
            vertices = io.getInt();
        }
        io.close();
    }
}

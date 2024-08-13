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

class UnionFind {                                              
    public int[] p;
    public int[] rank;
    public int[] size;
    public long[] total;
    public int[] moved;
  
    public UnionFind(int N) {
        p = new int[N + 1];
        moved = new int[N + 1];
        rank = new int[N + 1];
        size = new int[N + 1];
        total = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            p[i] = i;
            moved[i] = i;
            rank[i] = 0;
            size[i] = 1;
            total[i] = i;
        }
    }

    public int findSet(int i) {
        int parent = moved[i];
        while (parent != p[parent]) {
            parent = p[parent];
        }
        moved[i] = parent;
        return parent;
    }
  
    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  
    public void unionSet(int i, int j) { 
        if (!isSameSet(i, j)) { 
            int parent1 = findSet(i), parent2 = findSet(j);
            // rank is used to keep the tree short
            if (rank[parent1] > rank[parent2]) {

                p[parent2] = parent1;
                moved[i] = parent1;
                size[parent1] += size[parent2];
                total[parent1] += total[parent2];

            } else { 

                p[parent1] = parent2;
                moved[i] = parent2;
                size[parent2] += size[parent1];
                total[parent2] += total[parent1];

                if (rank[parent1] == rank[parent2]) {
                    rank[parent2] += 1;
                } 
            } 
        }
        
    }

    public void move(int i, int j) {
        if (!isSameSet(i, j)) {
            int dep = findSet(i);
            int des = findSet(j);
            
            moved[i] = des;

            size[dep] -= 1;
            total[dep] -= i;

            size[des] += 1;
            total[des] += i;

            if (rank[des] == 0) {
                rank[des] += 1;
            }
            
            if (rank[dep] == 1 && size[dep] == 2) {
                rank[dep] -= 1;
            }
        }
    }

    public int size(int i) {
        return size[findSet(i)];
    }

    public long total(int i) {
        return total[findSet(i)];
    }
}


public class almostunionfind {
    public static void main(String[] args) throws IOException{
        Kattio reader = new Kattio(System.in, System.out);

        while (reader.hasMoreTokens()) {
            int size = reader.getInt();
            int numOps = reader.getInt();

            UnionFind unionFind = new UnionFind(size);
            for (int i = 1; i <= numOps; i++) {
                int operation = reader.getInt();
                if (operation == 1) {
                    int num1 = reader.getInt();
                    int num2 = reader.getInt();
                    unionFind.unionSet(num1, num2);
                } else if (operation == 2) {
                    int num1 = reader.getInt();
                    int num2 = reader.getInt();
                    unionFind.move(num1, num2);
                } else {
                    int num1 = reader.getInt();
                    int count = unionFind.size(num1);
                    long sum = unionFind.total(num1);
                    reader.write(count + " " + sum + "\n");
                }
            }

        }
        reader.close();
    }
}

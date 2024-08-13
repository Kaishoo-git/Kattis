    import java.util.*;
    import java.io.*;

    public class dominos {
        public static void main(String[] args) throws IOException {
            Kattio io = new Kattio(System.in, System.out);
            int testCases = io.getInt();
            
            for (int i = 0; i < testCases; i++) {
                int n = io.getInt(); // Number of Dominos
                int m = io.getInt(); // Number of lines

                ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
                for (int j = 0; j < n; j++) {
                    ArrayList<Integer> neighbours = new ArrayList<Integer>();
                    adjList.add(neighbours);
                }
                // Create an adjacencyList
                for (int j = 0; j < m; j++) {
                    // 0-based Indexing, domino 1 = vertex 0
                    int u = io.getInt() - 1;
                    int v = io.getInt() - 1;
                    // u goes to v
                    adjList.get(u).add(v);
                }

                int[] visited = new int[n];

                // DFS Toposort
                LinkedList<Integer> toposort = new LinkedList<Integer>();
                for (int j = 0; j < n; j++) {
                    if (visited[j] == 0) {
                        Stack<Integer> stack = new Stack<Integer>();
                        stack.push(j);
                        while (!stack.isEmpty()) {
                            int u = stack.peek();
                            visited[u] = 1;
                            boolean flag = true;
                            for (int v : adjList.get(u)) {
                                // Not all vertices processed
                                // System.out.println(String.format("node %d",v));
                                if (visited[v] == 0) {
                                    stack.push(v);
                                    flag = false;
                                }
                            }
                            if (flag) {
                                // System.out.println(u);
                                u = stack.pop();
                                toposort.addFirst(u);
                            }
                        }
                    }
                }
                // System.out.println(toposort);
                
                visited = new int[n];
                int components = 0;
                // Count SCCs
                for (int j : toposort) {
                    // DFS for each unvisited component
                    if (visited[j] == 0) {
                        components += 1;
                        Stack<Integer> stack = new Stack<Integer>();
                        stack.push(j);
                        while (!stack.isEmpty()) {
                            int u = stack.peek();
                            visited[u] = 1;
                            boolean flag = true;
                            for (int v : adjList.get(u)) {
                                // Not all vertices processed
                                if (visited[v] == 0) {
                                    stack.push(v);
                                    flag = false;
                                }
                            }
                            if (flag) {
                                stack.pop();
                            }
                        }
                    }
                }
                System.out.println(components);
            }
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

import java.util.*;
import java.io.*;

public class humancannonball {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        double runSpeed = 5, cannonTime = 2;

        String[] line = br.readLine().split(" ");
        double sx = Double.parseDouble(line[0]); double sy = Double.parseDouble(line[1]);
        cood start = new cood(sx, sy);
        line = br.readLine().split(" ");
        double ex = Double.parseDouble(line[0]); double ey = Double.parseDouble(line[1]);
        cood end = new cood(ex, ey);

        int c = Integer.parseInt(br.readLine());
        HashMap<cood, ArrayList<edge>> adjList = new HashMap<>();
        HashMap<cood, Double> D = new HashMap<cood, Double>();

        adjList.put(start, new ArrayList<edge>()); D.put(start, 0.0);
        adjList.put(end, new ArrayList<edge>()); D.put(end, Double.MAX_VALUE);

        // Add cannons into cannons arrayList, vertices into adjList and D map<array>
        ArrayList<cood> cannons = new ArrayList<cood>();
        for (int i = 0; i < c; i++) {
            line = br.readLine().split(" ");
            cood cannon = new cood(Double.parseDouble(line[0]), Double.parseDouble(line[1])); 
            cannons.add(cannon);
            adjList.put(cannon, new ArrayList<edge>()); D.put(cannon, Double.MAX_VALUE);
        }
        adjList.get(start).add(new edge(Math.sqrt(Math.pow(start.x-end.x,2) + Math.pow(start.y-end.y,2)) / runSpeed, end));
        // Adding of edges from start -> cannon, cannon -> end, cannon -> others 
        for (cood cannon : cannons) {
            double timeFromStart = Math.sqrt(Math.pow(cannon.x-start.x,2) + Math.pow(cannon.y-start.y,2)) / runSpeed;
            double cirToEnd = Math.abs(50 - Math.sqrt(Math.pow(cannon.x-end.x,2) + Math.pow(cannon.y-end.y,2)));
            double timeToEnd = cannonTime + cirToEnd / runSpeed;
            adjList.get(start).add(new edge(timeFromStart, cannon));
            adjList.get(cannon).add(new edge(timeToEnd, end));
            for (cood o : cannons) {
                if (cannon != o) {
                    double timeToOther = cannonTime + (Math.abs(Math.sqrt(Math.pow(cannon.x-o.x,2) + Math.pow(cannon.y-o.y,2)) - 50) / runSpeed);
                    adjList.get(cannon).add(new edge(timeToOther, o));
                }
            }
        }

        /*for (Map.Entry<cood, ArrayList<edge>> set : adjList.entrySet()) {
            String string = "" + String.format("(%.1f, %.1f): ", set.getKey().x, set.getKey().y);
            for (edge e : set.getValue()) {
                string += String.format("<%.3f, (%.1f, %.1f) > ", e.w, e.v.x, e.v.y);
            }            
            pw.println(string);
        }*/

        // BellmanFord
        int V = adjList.size();
        for (int i = 0; i < V - 1; i++) {
            for (Map.Entry<cood, Double> set : D.entrySet()) { // Every vertex
                for (edge e : adjList.get(set.getKey())) { // Check Neighbours
                    if (set.getValue() != Double.MAX_VALUE && set.getValue() + e.w < D.get(e.v)) {
                        D.put(e.v, set.getValue() + e.w);
                    }
                }
            }
        }
        pw.println(D.get(end));
        pw.close();
    }
}

class cood{
    public double x, y;
    cood(double i, double j) {x = i; y = j;}
}

class edge implements Comparable<edge> {
    public double w;
    public cood v;
    edge(double i, cood j){w = i; v = j;}

    public int compareTo(edge o) {
        if (this.w<o.w){return -1;}
        if (this.w>o.w){return 1;}
        return 0;
    }
}

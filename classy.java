import java.util.*;
import java.io.*;

public class classy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Entity> array = new ArrayList<Entity>();
            for (int j = 0; j < n; j++) {
                String[] input = br.readLine().split(" ");
                String name = input[0].substring(0, input[0].length() - 1); 
                String[] hie = input[1].split("-"); int[] in = new int[hie.length];
                for (int k = 0; k < hie.length; k++) {
                    if (hie[k].equals("upper")) {in[k] = 3;}
                    else if (hie[k].equals("middle")) {in[k] = 2;}
                    else if (hie[k].equals("lower")) {in[k] = 1;}
                    else {in[k] = 0;}
                }
                array.add(new Entity(name, in));
            }
            Collections.sort(array);
            for (int k = 0; k < array.size(); k++) {pw.println(array.get(k).name);}
            pw.println("=".repeat(30));
        }
        pw.close();
    }
}

class Entity implements Comparable<Entity> {
    public String name;
    public int[] hie;
    Entity(String n, int[] l) {name = n; hie = l;}
    public int compareTo(Entity o) {
        for (int i = 1; i <= Math.min(this.hie.length, o.hie.length); i++) {
            if (this.hie[this.hie.length - i] > o.hie[o.hie.length - i]) {return -1;}
            if (this.hie[this.hie.length - i] < o.hie[o.hie.length - i]) {return 1;}
        }
        return this.name.compareTo(o.name); 
    }
}
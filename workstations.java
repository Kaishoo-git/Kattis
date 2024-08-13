import java.util.*;
import java.io.*;

class Researcher {
    int arrival;
    int time;

    Researcher(int arrive, int input) {
        this.arrival = arrive;
        this.time = input;
    }
}

class ResearcherComparator implements Comparator<Researcher> {
    @Override
    public int compare(Researcher one, Researcher two) {
        if (one.arrival > two.arrival) {
            return 1;
        } else if (one.arrival < two.arrival) {
            return - 1;
        } else {
            return 0;
        }
    }
}

public class workstations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        ArrayList<Researcher> arrayList = new ArrayList<Researcher>();
        int saved = 0;

        String[] input = br.readLine().split(" ");
        int idleTime = Integer.parseInt(input[1]);
        int numEmployees = Integer.parseInt(input[0]);
        
        for (int i = 1; i <= numEmployees; i++) {
            input = br.readLine().split(" ");
            arrayList.add(new Researcher(Integer.parseInt(input[0]), 
            Integer.parseInt(input[1])));
        }
        Collections.sort(arrayList, new ResearcherComparator());

        //PriorityQueue here represents nextAvailableTimes
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for (Researcher researcher : arrayList) {
            int arrive = researcher.arrival;
            int useTime = researcher.time;
            boolean flag = true;
            while (flag) {
                if (pq.peek() == null) {
                    flag = false;
                } else {
                    int nextAvailableTime = pq.peek();
                    if (arrive < nextAvailableTime) {
                        flag = false;
                    } else if ((arrive >= nextAvailableTime) && 
                    (arrive <= nextAvailableTime + idleTime)) {
                        saved = saved + 1;
                        pq.poll();
                        flag = false;
                    } else {
                        pq.poll();
                    }
                }
            }
            pq.add(arrive + useTime);
        }
        pw.print(saved);
        pw.close();
    }
}

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

class Researcher implements Comparable<Researcher> {
    int startTime;
    int workingTime;
    int endTime;

    public Researcher(int startTime, int workingTime) {
        this.startTime = startTime;
        this.workingTime = workingTime;
        this.endTime = startTime + workingTime;
    }

    @Override
    public int compareTo(Researcher other) {
        return Integer.compare(this.startTime, other.startTime);
    }
}

public class WorkstationUnlockings {

    public static int maxUnlockings(int numResearchers, int workstationLockDuration, List<Researcher> researchers) {
        Collections.sort(researchers); // Sort researchers based on start time
        PriorityQueue<Integer> workstationEndTimes = new PriorityQueue<>();

        for (Researcher researcher : researchers) 
        {
            // Check if any workstation's end time matches the start time of the current researcher
            boolean assigned = false;
            for (Integer endTime : workstationEndTimes) 
            {
                if (endTime == researcher.startTime || researcher.startTime > endTime 
                && researcher.startTime <= (endTime + workstationLockDuration)) 
                {
                    // Update the end time of this workstation
                    workstationEndTimes.remove(endTime);
                    workstationEndTimes.offer(researcher.endTime);
                    assigned = true;
                    break;
                } 
            }
            if (!assigned) {
                // Create a new workstation
                workstationEndTimes.offer(researcher.endTime);
            }
        }

        return numResearchers - workstationEndTimes.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        int numResearchers = sc.nextInt(); // Number of researchers
        int workstationLockDuration = sc.nextInt(); // Number of minutes till the workstation locks itself

        List<Researcher> researchers = new ArrayList<>();
        for (int i = 0; i < numResearchers; i++) {
            int startTime = sc.nextInt();
            int workingTime = sc.nextInt();
            researchers.add(new Researcher(startTime, workingTime));
        }

        // Output
        System.out.println(maxUnlockings(numResearchers, workstationLockDuration, researchers));

    }
}





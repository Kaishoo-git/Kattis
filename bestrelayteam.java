import java.io.*;
import java.util.*;

class Runner {
    String name;
    double ai;
    double bi;
    double diff;
    Runner(String nameInput, String firstLeg, String lastLeg) {
        this.name = nameInput;
        this.ai = Double.parseDouble(firstLeg);
        this.bi = Double.parseDouble(lastLeg);
        this.diff = this.ai - this.bi;
    }
}

class AiComparator implements Comparator<Runner>{
    @Override
    public int compare(Runner runnerOne, Runner runnerTwo) {
        if (runnerOne.ai < runnerTwo.ai) {
            return - 1;
        } else if (runnerOne.ai > runnerTwo.ai) {
            return 1;
        } else {
            return 0;
        }
    }
}

class BiComparator implements Comparator<Runner>{
    @Override
    public int compare(Runner runnerOne, Runner runnerTwo) {
        if (runnerOne.bi < runnerTwo.bi) {
            return - 1;
        } else if (runnerOne.bi > runnerTwo.bi) {
            return 1;
        } else {
            return 0;
        }
    }
}

class DiffComparator implements Comparator<Runner>{
    @Override
    public int compare(Runner runnerOne, Runner runnerTwo) {
        if (runnerOne.diff < runnerTwo.diff) {
            return - 1;
        } else if (runnerOne.diff > runnerTwo.diff) {
            return 1;
        } else {
            return 0;
        }
    }
}

public class bestrelayteam {
    public static ArrayList<Runner> processRunners(ArrayList<Runner> input) {
        if (input.size() <= 4) {
            Collections.sort(input, new DiffComparator());
            return input;
        }
        Collections.sort(input, new BiComparator());
        ArrayList<Runner> selectedRunners = new ArrayList<>(input.subList(0, 4));

        ArrayList<Runner> theRest = new ArrayList<>();
        if (input.size() > 4) {
            theRest.addAll(input.subList(4, input.size()));
        }
        Collections.sort(theRest, new AiComparator()) ;
        selectedRunners.add(theRest.get(0));

        ArrayList<ArrayList<Runner>> permutations = generatePermutations(selectedRunners);
        int fastestTimeIndex = 0;
        for (int i = 1; i < permutations.size(); i++) {
            double currentTime = getRunTime(permutations.get(i));
            if (currentTime < getRunTime(permutations.get(fastestTimeIndex))) {
                fastestTimeIndex = i;
            }
        }
        return permutations.get(fastestTimeIndex);
    }

    public static ArrayList<ArrayList<Runner>> generatePermutations(ArrayList<Runner> inputRunners) {
        ArrayList<ArrayList<Runner>> permutations = new ArrayList<>();
        for (int index = 0; index < inputRunners.size(); index++) {
            Runner runner = inputRunners.get(index);
            ArrayList<Runner> copy = new ArrayList<Runner>(inputRunners);
            ArrayList<Runner> permutation = new ArrayList<>();
            permutation.add(runner);
            copy.remove(runner);
            Collections.sort(copy, new BiComparator());
            permutation.addAll(copy.subList(0,3));
            permutations.add(permutation);
        }
        return permutations;
    }

    public static Double getRunTime(ArrayList<Runner> input) {
        double totalRunTime = 0.00;
        for (int i = 0; i < input.size(); i++) {
            Runner runner = input.get(i);
            if (i == 0) {
                totalRunTime = totalRunTime + runner.ai;
            } else {
                totalRunTime = totalRunTime + runner.bi; 
            }
        }
        return totalRunTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfRunners = Integer.parseInt(br.readLine());
        
        ArrayList<Runner> runners = new ArrayList<>();
        for (int i = 0; i < numOfRunners; i++) {
            String inputData = br.readLine();
            String[] data = inputData.split(" ");
            Runner runner = new Runner(data[0], data[1], data[2]);
            runners.add(runner);
        }

        ArrayList<Runner> selectedRunners = processRunners(runners);
        System.out.printf("%.9f\n", getRunTime(selectedRunners));

        for (int i = 0; i < selectedRunners.size(); i++) {
            Runner runner = selectedRunners.get(i);
            System.out.println(runner.name);
        }
    }
}

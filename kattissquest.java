import java.util.*;
import java.io.*;

class Quest {
    public int energy;
    public long gold;

    Quest(int e, long g) {
        this.energy = e;
        this.gold = g;
    }

    boolean equals(Quest quest) {
        if (this.energy == quest.energy && this.gold == quest.gold) {
            return true;
        } else {
            return false;
        }
    }
}

class QuestComparator implements Comparator<Quest> {
    @Override
    public int compare(Quest o1, Quest o2) {
        if (o1.energy < o2.energy) {
            return - 1;
        } else if (o1.energy > o2.energy) {
            return 1;
        } else {
            if (o1.gold < o2.gold) {
                return - 1;
            } else if (o1.gold > o2.gold) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}

public class kattissquest {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        TreeMap<Quest, Integer> tm = new TreeMap<Quest, Integer>(new QuestComparator());

        int numOps = Integer.parseInt(br.readLine());
        for (int i = 1; i <= numOps; i++) {
            String[] inputs = br.readLine().split(" ");
            if (inputs.length == 3) {
                int energy = Integer.parseInt(inputs[1]);
                long gold = Long.parseLong(inputs[2]);
                Quest quest = new Quest(energy, gold);
                if (tm.containsKey(quest)) {
                    tm.replace(quest, tm.get(quest) + 1);
                } else {
                    tm.put(quest, 1);
                }
            } else {
                long totalGold = 0;
                int totalEnergy = Integer.parseInt(inputs[1]);
                Quest placeHolder = new Quest(totalEnergy, 99999999);
                
                boolean flag = true;
                while (flag && totalEnergy > 0) {
                    Quest key = tm.floorKey(placeHolder);
                    if (key == null) {
                        flag = false;
                    } else {
                        int count = tm.get(key);
                        if (count > 1) {
                            tm.replace(key, tm.get(key) - 1);
                        } else {
                            tm.remove(key);
                        }
                        totalEnergy = totalEnergy - key.energy;
                        totalGold = totalGold + key.gold;
                        //pw.println(String.format("%d energy, %d gold", totalEnergy, totalGold));
                        placeHolder = new Quest(totalEnergy, 999999999);
                    }
                }
                pw.println(totalGold);
            }
        }
        pw.close();
    }

}

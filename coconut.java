import java.util.*;
import java.io.*;

class Hand {
    int owner;
    int state;

    Hand(int inputOwner, int inputState) {
        this.owner = inputOwner;
        this.state = inputState;
    }
}

public class coconut {
    public static Queue<Hand> initHands(int numPlayers) {
        Queue<Hand> handQueue = new LinkedList<Hand>();
        for (int i = 1; i <= numPlayers; i++) {
            handQueue.offer(new Hand(i, 3));
        }
        return handQueue;
    }

    public static int processQueue(Queue<Hand> handQueue, int syllabus) {
        while (handQueue.size() > 1) {
            for (int i = 1; i < syllabus; i++) {
                Hand currentHand = handQueue.poll();
                handQueue.offer(currentHand);
            }
            Hand targetHand = handQueue.poll();
            if (targetHand.state == 3) {
                Queue<Hand> newQueue = new LinkedList<Hand>();
                newQueue.offer(new Hand(targetHand.owner, 2));
                newQueue.offer(new Hand(targetHand.owner, 2));
                while (handQueue.peek() != null) {
                    Hand hand = handQueue.poll();
                    newQueue.offer(hand);
                }
                handQueue = newQueue;
            } else if (targetHand.state == 2) {
                handQueue.offer(new Hand(targetHand.owner, 1));
            }
        }
        Hand remainingHand = handQueue.poll();
        return remainingHand.owner;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputData = br.readLine();
        String[] data = inputData.split(" ");
        Queue<Hand> handQueue = initHands(Integer.parseInt(data[1]));
        int remainingPlayer = processQueue(handQueue, Integer.parseInt(data[0]));
        System.out.println(remainingPlayer);
    }
}
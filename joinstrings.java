import java.util.*;
import java.io.*;

class Node {
    String data;
    Node next;

    Node(String inputData) {
        this.data = inputData;
        this.next = null;
    }
}

class MyList {
    public Node head;
    public Node tail;

    MyList(Node headNode) {
        this.head = headNode;
        this.tail = headNode;
    }

    public void add(MyList myList) {
        this.tail.next = myList.head;
        this.tail = myList.tail;
    }
}
public class joinstrings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, MyList> hashmap = new HashMap<Integer, MyList>();
        int numStrings = Integer.parseInt(br.readLine());
        if (numStrings == 1) {
            String input = br.readLine();
            System.out.print(input);
        } else {
            for (int i = 1; i <= numStrings; i++) {
                String data = br.readLine();
                Node newNode = new Node(data);
                MyList myList = new MyList(newNode);
                hashmap.put(i, myList);
            }

            String[] stringArray = br.readLine().split(" ");
            int headPointer = Integer.parseInt(stringArray[0]);
            MyList head = hashmap.get(headPointer);
            MyList nextNode = hashmap.get(Integer.parseInt(stringArray[1]));
            head.add(nextNode);

            for (int i = 2; i <= numStrings - 1; i++) {
                String[] input = br.readLine().split(" ");
                int firstPointer = Integer.parseInt(input[0]);
                int secondPointer = Integer.parseInt(input[1]);
                if (secondPointer == headPointer) {
                    headPointer = firstPointer; 
                }
                MyList first = hashmap.get(firstPointer);
                MyList next = hashmap.get(secondPointer);
                first.add(next);
            }

            Node headNode = hashmap.get(headPointer).head;
            while (headNode != null) {
                System.out.print(headNode.data);
                headNode = headNode.next;
            }
        }
    }
}

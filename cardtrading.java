import java.io.*;
import java.util.*;

class Card {
    int type;
    int quantity;
    long buy;
    long sell;
    Card(int inputCardType, int numCards, long buyInput,long sellInput) {
        this.type = inputCardType;
        this.quantity = numCards;
        this.buy = buyInput;
        this.sell = sellInput;
    }

    long getSell() {
        return this.quantity * this.sell;
    }

    long getBuy() {
        return (2 - this.quantity) * this.buy;
    }

    long getMoneySaved() {
        return this.getBuy() + this.getSell();
    }
}

class ValueComparator implements Comparator<Card> {
    @Override
    public int compare(Card cardOne, Card cardTwo) {
        if (cardOne.getMoneySaved() > cardTwo.getMoneySaved()) {
            return 1;
        } else if (cardOne.getMoneySaved() < cardTwo.getMoneySaved()) {
            return - 1;
        } else {
            return 0;
        }
    }
}

public class cardtrading {
    public static int[] createDeck(String inputCards, int numTypes) {
        int[] deck = new int[numTypes];
        String[] cards = inputCards.split(" ");
        for (int index = 0; index < cards.length; index++) {
            int cardType = Integer.parseInt(cards[index]) - 1;
            deck[cardType]++;
        }
        return deck;
    }

    public static long calculateProfit(ArrayList<Card> catalogue, int targetCombo) {
        Collections.sort(catalogue, new ValueComparator());
        long sold = 0;
        long bought = 0;
        for (int i = 0; i < catalogue.size(); i++) {
            if (i < targetCombo ) {
                bought = bought + catalogue.get(i).getBuy();
            } else { 
                sold = sold + catalogue.get(i).getSell();
            }
        };
        return sold - bought;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] firstLine = input.split(" ");
        int numTypes = Integer.parseInt(firstLine[1]);
        int targetCombo = Integer.parseInt(firstLine[2]);

        String secondLine = br.readLine();
        int[] deck = createDeck(secondLine, numTypes);

        ArrayList<Card> catalogue = new ArrayList<>();
        for (int index = 0; index < numTypes; index++) {
            String newInput = br.readLine();
            String[] newInputSplit = newInput.split(" ");
            long buyPrice = Long.parseLong(newInputSplit[0]);
            long sellPrice = Long.parseLong(newInputSplit[1]);
            Card card = new Card(index + 1, deck[index], buyPrice, sellPrice);
            catalogue.add(card);
        }

        long profit = calculateProfit(catalogue, targetCombo);
        System.out.println(profit);
    }
}
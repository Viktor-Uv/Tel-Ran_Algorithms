package lesson3.blackjack;

import java.util.Random;

public class Dealer {
    private Random rand = new Random();

    public Card getCard() {
        int rank = rand.nextInt(13); // [0; 13)
        int suite = rand.nextInt(4); // [0; 4)
        return new Card(
                Rank.values()[rank],
                Suite.values()[suite]
        );
    }
}

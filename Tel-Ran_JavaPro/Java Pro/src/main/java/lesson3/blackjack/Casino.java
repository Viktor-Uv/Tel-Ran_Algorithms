package lesson3.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Casino {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        Scanner scanner = new Scanner(System.in);
        List<Card> hand = new ArrayList<>();
        do {
            Card card = dealer.getCard();
            hand.add(card);
            System.out.println("Card: " + card);
            int score = 0;
            for (Card c : hand) {
                score += c.getValue();
            }
            System.out.println("Your score: " + score);
            System.out.println("Press N to finish game");
        } while (!scanner.next().equals("N"));
    }
}

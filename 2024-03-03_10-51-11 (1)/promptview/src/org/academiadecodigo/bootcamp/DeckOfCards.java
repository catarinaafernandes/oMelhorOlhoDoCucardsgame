package org.academiadecodigo.bootcamp;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DeckOfCards {


    private List<Card> cards;

    public DeckOfCards() {
        cards = new ArrayList<>();
        String[] labels = {"3", "4", "5", "6", "7", "J", "D", "K", "A", "2"};
        int[] values = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        for (int i=0;i<labels.length;i++) {
            String label = labels[i];
            int value = values[i];
            cards.add(new Card(label,value));
            cards.add(new Card(label,value));
            cards.add(new Card(label,value));
            cards.add(new Card(label,value));
        }
        shuffle();
        //ver se funfa
    }

    public void shuffle() {
        Collections.shuffle(cards);

    }

    /*public void distributeCards(List<User> users, int cardsForUser) {

        for (int i = 0; i < cardsForUser; i++) {
            for (User user : users) {
                Card card = cards.remove(0);
                user.addCards(card);

            }
        }

    }*/
    public void distributeCards(List<User> users, int cardsForUser) {
        for (User user : users) {
            for(int i=0;i<cardsForUser; i++){
                Card card = cards.remove(0);
                user.addCards(card);
            }
        }
    }

}

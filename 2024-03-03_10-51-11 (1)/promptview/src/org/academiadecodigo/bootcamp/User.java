package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.scanners.string.PasswordInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private List<Card> cards;
    private int order =  0;
    private User playerThatSetHighestCard;

    Socket clientSocket;

    public User(Socket clientSocket) {
        this.cards = new ArrayList<>();
        this.clientSocket = clientSocket;
    }
    public User(String name) {
        this.cards = new ArrayList<>();
        this.name = name;
        //this.clientSocket = clientSocket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }
    public String getCardsValues() {
        StringBuilder values = new StringBuilder();
        for(Card card :this.cards){
            values.append(card.getValue());
        }
        return values.toString();
    }
    public String getCardsLabels() {
        StringBuilder labels = new StringBuilder();
        for(Card card :this.cards){
            labels.append(card.getLabel());
        }
        return labels.toString();
    }
    public void addCards(Card card){
        cards.add(card);
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }
    public int playCard(int cardPosition, int lastPlayedCardValue, boolean isFullLoop, User player){
        if(cardPosition > cards.size()-1){
            System.out.println("a jogada é inválida.");
            return lastPlayedCardValue;
        }
        Card card = cards.get(cardPosition);
        if(card != null && ((card.getValue() > lastPlayedCardValue && !isFullLoop) || (isFullLoop && player == this.playerThatSetHighestCard))) {
            System.out.println(this.name + "joga a carta: " + card.getLabel());
            lastPlayedCardValue = card.getValue();
            cards.remove(cardPosition);
            this.playerThatSetHighestCard = player;
        }else{
            System.out.println("a carta é menor ou igual do que a que está em jogo.");
        }
        return lastPlayedCardValue;
        //System.out.println(this.name+" plays the card: "+cards.get(cardPosition).getValue());
    }
}







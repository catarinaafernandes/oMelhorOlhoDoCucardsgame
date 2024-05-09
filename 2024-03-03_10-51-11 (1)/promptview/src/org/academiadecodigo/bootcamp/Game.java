package org.academiadecodigo.bootcamp;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    ArrayList<User> users;
    DeckOfCards deckOfCards;
    boolean isRunningGame = true;
    int currentHighestCardValue = 0;
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.gameStart();
    }
    public void gameStart() throws IOException {
        users = new ArrayList<>();
        DeckOfCards doc = new DeckOfCards();
        /*users.add(new User(new Socket("localhost",8080)));
        users.add(new User(new Socket("localhost",8081)));*/
        users.add(new User("ze"));
        users.add(new User("catia"));
        users.add(new User("jaycinta"));
        users.add(new User("esteves"));
        //users.add(new User());
        doc.distributeCards(users, 10);
        System.out.println(users.get(0).getName() + " cards: "+users.get(0).getCardsLabels());
        System.out.println(users.get(1).getName() + " cards: "+users.get(1).getCardsLabels());
        System.out.println(users.get(2).getName() + " cards: "+users.get(2).getCardsLabels());
        System.out.println(users.get(3).getName() + " cards: "+users.get(3).getCardsLabels());
        Random r = new Random();
        int low = 0;
        int high = users.size();
        int randomStartingPlayer = r.nextInt(high-low) + low;
        boolean isFullLoop = false;
        while(isRunningGame){
            Scanner playerInput = new Scanner(System.in);
            User currentPlayer = users.get(randomStartingPlayer);
            if(!currentPlayer.getCards().isEmpty()){
                System.out.println("Player "+currentPlayer.getName() +" pick a card");
                int pickedCardNumber = playerInput.nextInt();
                currentHighestCardValue = currentPlayer.playCard(pickedCardNumber,currentHighestCardValue, isFullLoop, currentPlayer);
            }
            if(randomStartingPlayer < users.size()-1){
                isFullLoop = false;
                randomStartingPlayer++;
            }else{
                isFullLoop = true;
                randomStartingPlayer = 0;
            }
            System.out.println(users.get(0).getName() + " cards: "+users.get(0).getCardsLabels());
            System.out.println(users.get(1).getName() + " cards: "+users.get(1).getCardsLabels());
            System.out.println(users.get(2).getName() + " cards: "+users.get(2).getCardsLabels());
            System.out.println(users.get(3).getName() + " cards: "+users.get(3).getCardsLabels());
        }
        /*
        for (int i = 0; i < 4; i++) {
            users.add(new User("Player " + (i + 1)));
        }

        deckOfCards.distributeCards(users,10);



         */

    }

    //public gamePlay(){


  //  }


}

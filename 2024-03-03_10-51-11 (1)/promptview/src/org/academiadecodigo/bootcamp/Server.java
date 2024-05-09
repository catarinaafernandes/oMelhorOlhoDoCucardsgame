package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int portNumber;

    ArrayList<Game> games;

    DeckOfCards deckOfCards;

    CopyOnWriteArrayList<Socket> users = new CopyOnWriteArrayList<>();


    public static void main(String[] args) throws IOException {
        /*Server server = new Server();
        server.startServer();*/
        Game game = new Game();
        game.gameStart();

    }

    public void startServer() throws IOException {
        setClientSocket();
    }


    public void setPortNumber() {
        System.out.println("Port number?");
        this.portNumber = 8080;
        System.out.println(portNumber);

    }

    public void setClientSocket() throws IOException {
        setPortNumber();
        ServerSocket serverSocket = new ServerSocket(portNumber);
        ExecutorService pool = Executors.newCachedThreadPool();

        while (true){
            Socket clientSocket = serverSocket.accept();
            /*
            Thread thread = new Thread(new Serverhelper(clientSocket));
            thread.start();


             */

            users.add(clientSocket);
            pool.submit(new Serverhelper(clientSocket));

            System.out.println("Connection established");
        }

    }

    public String getUserName(){
        Prompt prompt = new Prompt(System.in,System.out);
        StringInputScanner inputScanner = new StringInputScanner();
        inputScanner.setMessage("Whats your username? ");

        String answer = prompt.getUserInput(inputScanner);

        return answer;
    }

    public void broadCast(String playerMessage) throws IOException {
        /*OutputStream outputStream =
        for (Socket user : users){
            user.getOutputStream().write(playerMessage.getBytes());

        }*/
    }


    private class Serverhelper implements Runnable {

        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public Serverhelper(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                out = new PrintWriter(this.clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
                out.println("Whats your name");
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {

            try {
                String name = in.readLine();
                Thread.currentThread().setName(name);
                    while (true) {
                        String playerMessage = in.readLine();
                        broadCast(playerMessage);

                    }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }



        public String readMessage(BufferedReader in) {

            String messageReceived = "";
            try {
                messageReceived = in.readLine();
                System.out.println(messageReceived);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return messageReceived;
        }

        public void writeMessage(PrintWriter out,String message) {
            out.println(message);
        }
    }



    }



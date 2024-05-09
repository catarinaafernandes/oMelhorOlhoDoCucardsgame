package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server2 {
    private static final int PORT = 8080;
    //private List<ClientHandler> users = new ArrayList<>();
    private List<Socket> users = new ArrayList<>();

    public Server2() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running on port " + PORT);
            ExecutorService pool = Executors.newCachedThreadPool();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                //ClientHandler clientHandler = new ClientHandler(clientSocket);
                //users.add(clientHandler);
                users.add(clientSocket);
                //Thread clientThread = new Thread(clientHandler);
                //clientThread.start();

                pool.submit(new ClientHandler(clientSocket,users));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server2();
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String name;
        private Prompt prompt;
        private StringInputScanner input;
        private Game game;
        private List<Socket> players;


        public ClientHandler(Socket clientSocket, List<Socket> players) throws IOException {
            this.clientSocket = clientSocket;
            this.players = players;

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());

            prompt = new Prompt(clientSocket.getInputStream(), new PrintStream(clientSocket.getOutputStream()));
            input = new StringInputScanner();

          //  game = new Game(players);


        }

        @Override
        public void run() {
          //  game.gameStart();

            while (true) {

                input.setMessage("Enter your name: ");
                name = prompt.getUserInput(input);

                out.println("HIII " + name);
                out.flush();

            }
        }


        private void sendMessage(String message) throws IOException {
            for (Socket client : users) {
                out = new PrintWriter(client.getOutputStream());
                out.println(message);
                out.flush();
            }
        }





    }

    public List<Socket> getUsers() {
        return users;
    }

    public void setUsers(List<Socket> users) {
        this.users = users;
    }
}

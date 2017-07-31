package com.ct.game.utils;

import com.badlogic.gdx.utils.*;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;


public class Server implements Disposable {
    private ServerThread serverThread;
    //private ServerSocketHints hints;

    public static void main(String[] args) {
        Server server = new Server();
    }

    public Server() {
        //this.hints = new ServerSocketHints();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(3000);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                                       + 3000 + " or listening for a connection");
            e.printStackTrace();
        }

        this.serverThread = new ServerThread(serverSocket);
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(serverThread, 0, 10, TimeUnit.MILLISECONDS);
    }

    @Override
    public void dispose() {

    }
}


class ServerThread extends Thread {
    ServerThread(final ServerSocket serverSocket) {
        super(new Runnable() {
            @Override
            public void run() {
                System.out.println("Listening");
                try {
                    Socket clientSocket = serverSocket.accept();
                    if (clientSocket != null) {
                        System.out.println("New Client Connected");
                        MultiServerThread multiServerThread = new MultiServerThread(clientSocket);
                        multiServerThread.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

class MultiServerThread extends Thread {
    MultiServerThread(final Socket clientSocket) {
        super(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        PrintWriter out =
                                new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));
                        //out.println("Hello from server!");
                        if (in.ready()) {
                            String message = in.readLine();
                            System.out.println(message);

                            if(message.equals("fin")){
                                clientSocket.close();
                                System.out.println("Client has disconnected");
                                break;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

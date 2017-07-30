package com.ct.game.utils;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket client = null;
    private ClientThread clientThread;

    public Client() {
        try {
            client = new Socket("localhost", 3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientThread = new ClientThread(client);
    }

    public void start() {
        clientThread.start();
    }

    public void end() {
        try {
            sendMessage("fin");
            client.shutdownInput();
            client.shutdownOutput();
            client.close();
            clientThread.join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            PrintWriter out =
                    new PrintWriter(client.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientThread extends Thread {
    ClientThread(final Socket client) {
        super(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        PrintWriter out =
                                new PrintWriter(client.getOutputStream(), true);
                        BufferedReader in =
                                new BufferedReader(
                                        new InputStreamReader(client.getInputStream()));
                        BufferedReader stdIn =
                                new BufferedReader(
                                        new InputStreamReader(System.in));
                        //out.println("Hello from client");

                        if (in.ready()) {
                            System.out.println(in.readLine());
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

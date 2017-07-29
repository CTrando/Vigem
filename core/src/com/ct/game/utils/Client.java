package com.ct.game.utils;

import java.io.*;
import java.net.Socket;

public class Client {
    private ClientThread clientThread;

    public Client() {
        clientThread = new ClientThread();
    }

    public void start() {
        clientThread.start();
    }
}

class ClientThread extends Thread {
    ClientThread() {
        super(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = new Socket("localhost", 3000);

                    while(true) {
                        PrintWriter out =
                                new PrintWriter(client.getOutputStream(), true);
                        BufferedReader in =
                                new BufferedReader(
                                        new InputStreamReader(client.getInputStream()));
                        BufferedReader stdIn =
                                new BufferedReader(
                                        new InputStreamReader(System.in));
                        out.println("HEllo from client");

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

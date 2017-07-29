package com.ct.game.utils;

import com.badlogic.gdx.utils.*;

import java.io.*;
import java.net.*;


public class Server implements Disposable {
    private ServerThread serverThread;
    //private ServerSocketHints hints;

    public Server() {
        //this.hints = new ServerSocketHints();
        this.serverThread = new ServerThread();
        serverThread.start();
    }


    @Override
    public void dispose() {

    }
}


class ServerThread extends Thread {
    ServerThread() {
        super(new Runnable() {
            @Override
            public void run() {
                    System.out.println("Listening");
                    try {
                        ServerSocket serverSocket = new ServerSocket(3000);
                        Socket clientSocket = serverSocket.accept();
                        while(true) {
                            PrintWriter out =
                                    new PrintWriter(clientSocket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(clientSocket.getInputStream()));
                            out.println("Hello from server!");
                            if(in.ready()){
                                System.out.println(in.readLine());
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Exception caught when trying to listen on port "
                                                   + 3000 + " or listening for a connection");
                        e.printStackTrace();
                    }

                /*ServerSocketHints hints = new ServerSocketHints();
                ServerSocket serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, "localhost",9021, hints);
                while(true) {
                    System.out.println("hi from thread 1");
                    try {
                        Socket socket = serverSocket.accept(null);
                        if (socket != null) {
                            BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        }
                    } catch(GdxRuntimeException e) {
                        System.out.println(e.getStackTrace());
                    }
                }*/
            }
        });
    }

}

package com.ct.game.utils;

import com.badlogic.gdx.*;
import com.badlogic.gdx.net.*;
import com.badlogic.gdx.utils.*;

import java.io.*;


public class Server implements Disposable {
    private ServerThread serverThread;
    private ServerSocketHints hints;

    public Server() {
        this.hints = new ServerSocketHints();
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
                ServerSocketHints hints = new ServerSocketHints();
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
                }
            }
        });
    }

}

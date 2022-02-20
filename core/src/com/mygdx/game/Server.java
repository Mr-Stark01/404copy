package com.mygdx.game;

import java.net.*;
import java.io.*;
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
            String inputLine;
            while ((inputLine = in.readUTF()) != null) {
                System.out.println(inputLine);
                if (".".equals(inputLine)) {
                    out.writeUTF("shit it worked");
                    break;
                }
                out.writeUTF(inputLine);
            }
        }catch (IOException e){

        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
         }catch (IOException e){

        }
    }

}

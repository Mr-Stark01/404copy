package com.mygdx.game;

import java.net.*;
import java.io.*;
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                if (".".equals(inputLine)) {
                    out.println("good bye");
                    break;
                }
                out.println(inputLine);
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

package com.mygdx.game;

import com.mygdx.game.units.Knight;

import java.net.*;
import java.io.*;
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());

            objectOut= new ObjectOutputStream(clientSocket.getOutputStream());
            objectIn= new ObjectInputStream(clientSocket.getInputStream());


            String inputLine;
            while ((inputLine = in.readUTF()) != null) {
                System.out.println(inputLine);
                out.writeUTF("asd");
                try {
                    Knight knight = (Knight) objectIn.readObject();
                    knight.getDamaged();
                    knight.getDamaged();
                    knight.getDamaged();
                    knight.getDamaged();
                    objectOut.writeObject(knight);
                }catch (ClassNotFoundException a){

                }
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

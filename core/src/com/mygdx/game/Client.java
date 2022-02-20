package com.mygdx.game;

import com.mygdx.game.units.Knight;

import java.net.*;
import java.io.*;

public class Client {
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());


            objectOut= new ObjectOutputStream(clientSocket.getOutputStream());
            objectIn= new ObjectInputStream(clientSocket.getInputStream());

        }catch (IOException e){

        }
    }

    public String sendMessage(String msg) {

        String resp ="Nem lett valami jó";
        try {
            out.writeUTF("idfk");
            System.out.println("here");
             resp =in.readUTF();
             Knight asdknight=new Knight();
            System.out.println("here");
            objectOut.writeObject(asdknight);
            System.out.println("here");
            try {
                System.out.println("here");
                asdknight = (Knight) objectIn.readObject();
                System.out.println("here");
                resp = String.valueOf(asdknight.attack());
            }catch (Exception a){

            }
            System.out.println("here");

        }catch (IOException e){

        }
        return resp;
    }

    public void tearDown(){
        this.stopConnection();
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        }catch (IOException e){

        }

    }
}
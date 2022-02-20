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
        String resp ="";
        try {
            out.writeUTF("idfk");
             resp =in.readUTF();
             Knight asdknight=new Knight();
            try {
                asdknight = (Knight) objectIn.readObject();
                resp = String.valueOf(asdknight.attack());
            }catch (Exception a){

            }
             objectOut.writeObject(asdknight);

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
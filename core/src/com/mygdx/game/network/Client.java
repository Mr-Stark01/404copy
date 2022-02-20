package com.mygdx.game.network;

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
            out.writeUTF(msg);
        }catch (IOException e){

        }
        return resp;
    }

    public Serializable sendObject(Serializable object){
        try {
            objectOut.writeObject(object);
            object =(Serializable) objectIn.readObject();
        }catch (Exception a){

        }

        return object;
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
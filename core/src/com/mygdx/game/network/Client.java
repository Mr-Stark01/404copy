package com.mygdx.game.network;

import com.mygdx.game.Castle;

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

    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
        }catch (IOException e){

        }

    }

    public void sendObject(Serializable object){
        try {
            objectOut.writeObject(object);
        }catch (Exception a){

        }
    }

    public String receiveMessage(){
        String receive="error";
        try{
            receive=in.readUTF();
        }catch (IOException e){

        }
        return receive;
    }
    public Castle receiveObject(){
        Castle receive=new Castle();
        try{
            receive=(Castle)objectIn.readObject();
        }catch (Exception e){

        }
        return receive;
    }


    public void stopConnection() {
        try {
            in.close();
            out.close();
            objectOut.close();
            objectIn.close();
            clientSocket.close();
        }catch (IOException e){

        }

    }



}
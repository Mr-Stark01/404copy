package com.mygdx.game.network;

import com.mygdx.game.Castle;

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

            objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
            objectIn = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (IOException e){

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

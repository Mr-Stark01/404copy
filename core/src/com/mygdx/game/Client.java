package com.mygdx.game;

import java.net.*;
import java.io.*;

public class Client {
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
        }catch (IOException e){

        }
    }

    public String sendMessage(String msg) {
        String resp ="";
        try {
            out.writeUTF("idfk");
             resp =in.readUTF();

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
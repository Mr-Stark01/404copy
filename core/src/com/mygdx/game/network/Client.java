package com.mygdx.game.network;

import com.mygdx.game.Castle;

import java.io.*;
import java.net.Socket;

public class Client {
  private Socket clientSocket;
  private DataOutputStream out;
  private DataInputStream in;
  private ObjectInputStream objectIn;
  private ObjectOutputStream objectOut;
  boolean buildRound;

  public void startConnection(String ip, int port) {
    try {
      clientSocket = new Socket(ip, port);
      out = new DataOutputStream(clientSocket.getOutputStream());
      in = new DataInputStream(clientSocket.getInputStream());
      objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
      objectIn = new ObjectInputStream(clientSocket.getInputStream());
    } catch (IOException e) {

    }
  }

  public boolean isConnected() {
    return clientSocket.isConnected();
  }

  public void sendMessage(String msg) {
    try {
      out.writeUTF(msg);
    } catch (IOException e) {
    }
  }

  public void sendObject(Serializable object) {
    try {
      objectOut.writeObject(object);
    } catch (Exception a) {
      System.out.println(a);
    }
    try {
      objectOut.reset();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public String receiveMessage() {
    String receive = "error";
    try {
      receive = in.readUTF();
    } catch (IOException e) {
      System.out.println(e);
    }
    try {
      in.reset();
    } catch (IOException e) {
      System.out.println(e);
    }
    return receive;
  }

  public Castle receiveObject() {
    buildRound=true;
    Castle receive = new Castle("Server",buildRound);
    try {
      receive = (Castle) objectIn.readObject();
    } catch (Exception e) {
      System.out.println(e);
    }
    try {
      objectOut.reset();
    } catch (IOException e) {
      System.out.println(e);
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
    } catch (IOException e) {
    }
  }
}

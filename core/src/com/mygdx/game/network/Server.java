package com.mygdx.game.network;

import com.mygdx.game.Castle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private DataOutputStream out;
  private DataInputStream in;
  private ObjectOutputStream objectOut;
  private ObjectInputStream objectIn;
  protected boolean buildRound;
  private int backlog;

  public void start(int port) {
    try {
      serverSocket = new ServerSocket(port);//, backlog,InetAddress.getByName("localhost"));
      clientSocket = serverSocket.accept();
      out = new DataOutputStream(clientSocket.getOutputStream());
      in = new DataInputStream(clientSocket.getInputStream());
      objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
      objectIn = new ObjectInputStream(clientSocket.getInputStream());
    } catch (IOException e) {
      System.out.println(e + "here7");
    }
    }

  public void sendMessage(String msg) {
    try {
      out.writeUTF(msg);
    } catch (IOException e) {
      System.out.println(e+"here6");
    }
  }

  public synchronized void sendObject(Serializable object) {
    try {
      objectOut.writeObject(object);
    } catch (Exception a) {
      a.printStackTrace();
      System.out.println(a.getLocalizedMessage()+"here5");
    }
    try {
      objectOut.reset();
    } catch (IOException e) {
      System.out.println(e+"here4");
    }
  }

  public String receiveMessage() {
    String receive = "error";
    try {
      receive = in.readUTF();
    } catch (IOException e) {
      System.out.println(e+"here3");
    }
    return receive;
  }

  public Castle receiveObject() {
    buildRound=true;
    Castle receive = new Castle("Client",buildRound);
    try {
      receive = (Castle) objectIn.readObject();
    } catch (Exception e) {
      System.out.println(e+"here1");
    }
    return receive;
  }

  public boolean isConnected() {
    return !serverSocket.isClosed();
  }

  public void stop() {
    try {
      in.close();
      out.close();
      clientSocket.close();
      serverSocket.close();
    } catch (IOException e) {
      System.out.println(e+"here2");
    }
  }
}

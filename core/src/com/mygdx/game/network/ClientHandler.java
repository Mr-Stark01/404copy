package com.mygdx.game.network;

import com.mygdx.game.Castle;

public class ClientHandler implements NetworkHandler, Runnable {
  private final Client client;
  private Castle ownCastle;
  private Castle enemyCastle;
  private Thread t;
  private final String threadName = "Steve";
  private final String ip;
  private boolean newCastle=true;

  public ClientHandler(Client client, String ip) {
    this.client = client;
    this.ip = ip;
  }

  @Override
  public void run() {
    System.out.println("Client Started");
    client.startConnection(ip, 6666);
    enemyCastle = client.receiveObject();
    client.sendObject(ownCastle);
    while (client.isConnected()) {
        enemyCastle = client.receiveObject();
        newCastle=true;
        client.sendObject(ownCastle);
        try{
          Thread.sleep(500);
        }
        catch(Exception e){

       }
    }
    client.stopConnection();
  }

  public synchronized void setCastle(Castle ownCastle) {
    this.ownCastle = ownCastle;
  }

  public Castle getEnemyCastle() {
    newCastle=false;
    return enemyCastle;
  }

  public boolean isNew(){
    return newCastle;
  }

  public boolean castleArrived(){
    return enemyCastle!=null;
  }

  public void start() {
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }
}

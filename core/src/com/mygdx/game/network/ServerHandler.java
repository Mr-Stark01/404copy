package com.mygdx.game.network;

import com.mygdx.game.Castle;

import java.util.concurrent.locks.ReentrantLock;

public class ServerHandler implements NetworkHandler, Runnable {
  private final ReentrantLock lock = new ReentrantLock();
  private final Server server;
  private Castle ownCastle;
  private Castle enemyCastle;
  private Thread t;
  private final String threadName = "John";
  private boolean newCastle=true;

  public ServerHandler(Server server) {
    this.server = server;
  }

  @Override
  public void run() {
    System.out.println("Server handler started");
    server.start(6666);
    server.sendObject(ownCastle);
    enemyCastle = server.receiveObject();
    while (server.isConnected()) {
      synchronized (ownCastle) {
        server.sendObject(ownCastle);
      }
        enemyCastle = server.receiveObject();
        newCastle=true;
      try{
        Thread.sleep(500);
      }
      catch(Exception e){

      }
    }
  }

  public synchronized void setCastle(Castle ownCastle) {
          this.ownCastle = ownCastle;
  }

  public boolean isNew(){
    return newCastle;
  }
  public boolean castleArrived(){
    return enemyCastle!=null;
  }

  public synchronized Castle getEnemyCastle() {
    newCastle=false;
    return enemyCastle;
  }

  public void start() {
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }
}
package com.mygdx.game.network;

import com.mygdx.game.Castle;
import java.util.concurrent.locks.ReentrantLock;

public class ServerHandler implements NetworkHandler, Runnable {
  private ReentrantLock lock = new ReentrantLock();
  private final Server server;
  private Castle ownCastle;
  private Castle enemyCastle;
  private Thread t;
  private final String threadName = "John";

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
      if (ownCastle.getUnits().size() > 0) {
        System.out.println(ownCastle.getUnits().get(0).getX()+"inside the method");
        }
        server.sendObject(ownCastle);
        enemyCastle = server.receiveObject();
      if (enemyCastle.getUnits().size() > 0) {
        System.out.println(enemyCastle.getUnits().get(0).getX()+"enemy server hacastle");
      }
    }
  }

  public synchronized void setCastle(Castle ownCastle) {
          this.ownCastle = ownCastle;
  }

  public synchronized Castle getEnemyCastle() {
    return enemyCastle;
  }

  public void start() {
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }
}
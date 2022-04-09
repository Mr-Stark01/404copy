package com.mygdx.game.network;

import com.badlogic.gdx.utils.Null;
import com.mygdx.game.Castle;

public class ClientHandler implements NetworkHandler, Runnable {
  private final Client client;
  private Castle ownCastle;
  private Castle enemyCastle;
  private Thread t;
  private final String threadName = "Steve";
  private final String ip;

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
        client.sendObject(ownCastle);
    }
    client.stopConnection();
  }

  public synchronized void setCastle(Castle ownCastle) {
    this.ownCastle = ownCastle;
  }

  public Castle getEnemyCastle() {
    return enemyCastle;
  }

  public void start() {
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }
}

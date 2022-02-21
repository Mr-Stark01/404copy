package com.mygdx.game.network;

import com.mygdx.game.Castle;

public class ClientHandler implements Runnable{
    private Client client;
    private Castle ownCastle;
    private Castle enemyCastle;
    private Thread t;
    private String threadName="Steve";
    private String ip;
    public ClientHandler(Client client,String ip){
        this.client=client;
        this.ip=ip;
    }

    @Override
    public void run() {
        System.out.println("Client Started");
        client.startConnection("192.168.0.210", 6666);
        enemyCastle=client.receiveObject();
        while (client.receiveMessage()!="STOP"){
            ownCastle.update(client.receiveObject());
            client.sendObject(enemyCastle);
        }
        client.stopConnection();
        System.out.println("the end Client");
    }

    public void setCastle(Castle ownCastle){
        this.ownCastle=ownCastle;
    }

    public Castle getEnemyCastle(){
        return enemyCastle;
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}

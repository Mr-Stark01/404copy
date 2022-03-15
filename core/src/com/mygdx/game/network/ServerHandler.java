package com.mygdx.game.network;

import com.mygdx.game.Castle;

public class ServerHandler implements NetworkHandler ,Runnable{
    private Server server;
    private Castle ownCastle;
    private Castle enemyCastle;
    private Thread t;
    private String threadName="John";

    public ServerHandler(Server server){
        this.server=server;
    }
    @Override
    public void run() {

        server.start(6666);


        server.sendObject(ownCastle);
        enemyCastle=server.receiveObject();


        while(server.isConnected()){
            server.sendObject(ownCastle);
            enemyCastle=server.receiveObject();

        }

    }

    public void setCastle(Castle ownCastle){
        this.ownCastle=ownCastle;

    }



    public synchronized Castle getEnemyCastle(){
        return enemyCastle;
    }

    public void start () {

        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}

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
        System.out.println("Server Started");
        server.start(6666);


        server.sendObject(ownCastle);
        enemyCastle=server.receiveObject();
        System.out.println(enemyCastle.getId());

        while(server.isConnected()){
            server.sendObject(ownCastle);
            enemyCastle.update(server.receiveObject());

        }
        System.out.println("the end Client");
    }

    public void setCastle(Castle ownCastle){
        this.ownCastle=ownCastle;
        ownCastle.setId("Server");
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

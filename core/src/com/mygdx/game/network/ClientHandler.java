package com.mygdx.game.network;

import com.mygdx.game.Castle;

public class ClientHandler implements NetworkHandler ,Runnable{
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
        client.startConnection(ip, 6666);

        enemyCastle=client.receiveObject();
        System.out.println(enemyCastle.getId());
        client.sendObject(ownCastle);

        while (client.isConnected()){

            enemyCastle.update(client.receiveObject());
            client.sendObject(ownCastle);

            try {
                wait(10);
            }catch (InterruptedException e){

            }

        }

        client.stopConnection();
        System.out.println("the end Client");
    }

    public synchronized void setCastle(Castle ownCastle){
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

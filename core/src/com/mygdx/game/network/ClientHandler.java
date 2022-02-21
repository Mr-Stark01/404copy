package com.mygdx.game.network;

public class ClientHandler implements Runnable{
    private Client client;

    private Thread t;
    private String threadName="Steve";
    public ClientHandler(Client client){
        this.client=client;
    }
    @Override
    public void run() {
        System.out.println("Client Started");
        client.startConnection("192.168.0.210", 6666);
        client.sendMessage("asd");
        client.stopConnection();
        System.out.println("the end Client");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}

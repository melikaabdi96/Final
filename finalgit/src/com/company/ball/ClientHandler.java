package com.company.ball;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket client;
    private int clientNum;
    private ArrayList<GameModel> players;

    public ClientHandler(Socket client, int clientNum) {
        this.client = client;
        this.clientNum = clientNum;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));

            String clientData = "";
            while (true) {
                String request = in.readUTF();

                if (!request.equals("over\n")) {
                    if (!request.equals(getPlayerNames())) {
                        GameModel gameModel = new GameModel(request);
                        players.add(gameModel);
                        clientData = gameModel.toString();
                        GameFile.objectFileWriter(gameModel);

                        System.out.print("Client" + clientNum + ": " + clientData);
                        out.writeUTF(clientData);
                        out.flush();
                    }else {
                        System.out.println("A user with this username is available!");
                    }
                } else {
                    System.out.println("All client" + clientNum + " messages are sent.");
                    break;
                }
            }
            in.close();
            out.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPlayerNames(){
        for (GameModel gameModel: players){
            return gameModel.getUserName();
        }
        return null;
    }
}

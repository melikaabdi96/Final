package com.company.ball;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket client = new Socket("127.0.0.1", 6023);) {
            System.out.println("Client connected.");

            DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));

            Scanner scanner = new Scanner(System.in);
            while (true) {
                //GameModel gameModel = new GameModel("melika");
                System.out.print("Enter username: ");
                String username = scanner.nextLine() + "\n";
               // GameModel gameModel = new GameModel(username);
                //String clientRequest = gameModel.toString();
                out.writeUTF(username);
                out.flush();
                System.out.println("User sent.");
                //System.out.println("Request sent.");
                if(username.equals("over\n"))  break;
                System.out.print("Server response: " + "\n" + in.readUTF() + "\n");
                //GameFile.objectFileWriter(gameModel);
            }
            System.out.println("All messages sent.");
            scanner.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client closed.");
    }
}

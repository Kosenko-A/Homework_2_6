package ru.geekbrains.homeworks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        new Server();
    }
    public Server() {
        try {
            System.out.println("Server is starting...");
            ServerSocket serverSocket = new ServerSocket(14883);

            System.out.println("Server is waiting for connection...");
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client connected: " + clientSocket);

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            Thread th1 = new Thread(){
                @Override
                public void run() {
                    super.run();
                    while (true){
                        String message = null;
                        try {
                            message = in.readUTF();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(message);
                    }
                }
            };
            Thread th2 = new Thread(){
                @Override
                public void run() {
                    super.run();
                    while (true) {
                        Scanner scan = new Scanner(System.in);
                        String servMessage = scan.nextLine();
                        try {
                            out.writeUTF(servMessage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            th1.start();
            th2.start();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

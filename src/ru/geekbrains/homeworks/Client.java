package ru.geekbrains.homeworks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        new Client();
    }
    public Client(){
        try {
            Socket clientSocket = new Socket("127.0.0.1", 14883);

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            Thread th1 = new Thread(){
                @Override
                public void run() {
                    super.run();
                    while (true) {
                        String servMessage = null;
                        try {
                            servMessage = in.readUTF();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(servMessage);
                    }
                }
            };

            Thread th2 = new Thread(){
                @Override
                public void run() {
                    super.run();
                    while (true){
                        Scanner scanner = new Scanner(System.in);
                        String message = scanner.nextLine();
                        try {
                            out.writeUTF(message);
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

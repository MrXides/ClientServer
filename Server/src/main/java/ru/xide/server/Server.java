package ru.xide.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8000)) {
            System.out.println("Сервер запущен");

            while (true)
                try (
                        Socket socket = server.accept();

                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream()));

                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()));
                ) {
                    String request = reader.readLine();
                    writer.write("Test");
                    writer.newLine();
                    writer.flush();
                    System.out.println("Клиент обнаружен :" + request.length());
                    System.out.println(request);
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

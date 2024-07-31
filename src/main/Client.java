package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter IP address: ");
        String ipAddress = scanner.nextLine();

        System.out.print("Enter port number (e.g., 8081): ");
        int port = Integer.parseInt(scanner.nextLine());

        Socket socket = new Socket(ipAddress, port);
        System.out.println("Connected to server...");

        // Create separate threads for reading from and writing to the server
        Thread readThread = new Thread(() -> readFromServer(socket));
        Thread writeThread = new Thread(() -> writeToServer(socket));

        // Start the threads
        readThread.start();
        writeThread.start();
    }

    private static void readFromServer(Socket socket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverMessage;
            while ((serverMessage = reader.readLine()) != null) {
                System.out.println("Server says: " + serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToServer(Socket socket) {
        try {
            Scanner scanner = new Scanner(System.in);
            OutputStream outputStream = socket.getOutputStream();
            while (true) {
                String clientMessage = scanner.nextLine();
                outputStream.write((clientMessage + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the server port number (e.g., 8081): ");
        int port = Integer.parseInt(scanner.nextLine());

        ServerSocket server = new ServerSocket(port);
        System.out.println("Listening for connection on port " + port + "....");

        while (true) {
            Socket clientSocket = server.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // Create separate threads for reading from and writing to the client
            Thread readThread = new Thread(() -> readFromClient(clientSocket));
            Thread writeThread = new Thread(() -> writeToClient(clientSocket));

            // Start the threads
            readThread.start();
            writeThread.start();
        }
    }

    private static void readFromClient(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                System.out.println("Received from client: " + clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToClient(Socket clientSocket) {
        try {
            Scanner scanner = new Scanner(System.in);
            OutputStream outputStream = clientSocket.getOutputStream();
            while (true) {
                String serverMessage = scanner.nextLine();
                outputStream.write((serverMessage + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

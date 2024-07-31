# Basic Socket Chat App

This is a simple chat application implemented using Java Sockets. The application includes a server and a client that can communicate with each other over a specified port.

## Features

- Server listens for incoming connections and handles multiple clients using threads.
- Client connects to the server and allows for bidirectional communication.
- Real-time messaging between server and client.

## Requirements
- Java Development Kit (JDK) 8 or higher

## How to Use

### Server

1. Compile and run the `Server` class.
2. Enter the port number when prompted (e.g., 8081).
3. The server will start listening for client connections on the specified port.

### Client

1. Compile and run the `Client` class.
2. Enter the server's IP address when prompted.
3. Enter the port number to connect to the server (should be the same as the server's port).
4. The client will connect to the server and you can start exchanging messages.

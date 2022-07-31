package server;

import lombok.extern.java.Log;
import properties.TransferProperties;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Log
public class Server extends Thread{

     private final ServerSocket serverSocket;
     private final List<ClientHandler> clients;

     private Boolean running = true;

    public Server() throws IOException {
        serverSocket = new ServerSocket(Integer.parseInt(TransferProperties.getInstance().getProperty("server_port")));
        clients = new ArrayList<>();
    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    @Override
    public void run() {
        log.info("Server listening on port: " + serverSocket.getLocalPort());

        while (running){
            try (Socket socket = serverSocket.accept()) {
                ClientHandler clientHandler = new ClientHandler(socket,this);
                clients.add(clientHandler);
                clientHandler.start();

            } catch (IOException e) {
                //or server stopped
                log.info("Client did not connect: " + e.getMessage());
            }
        }
        stopAllThreads();
    }

    private void stopAllThreads() {
        for (ClientHandler client : clients) {
            try {
                client.getSocket().close();

            } catch (IOException ex) {
                log.info("Client thread failed to stop: " + ex.getMessage());
            }
        }
    }

    public void terminate() {
        running = false;
        try {
            serverSocket.close();

        } catch (IOException e) {
            log.info("Failed to terminate server" + e.getMessage());
        }
    }

}

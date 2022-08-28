package server;

import lombok.extern.java.Log;
import network.Response;
import network.ResponseStatus;
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

    public Boolean getRunning(){
        return running;
    }

    @Override
    public void run() {
        log.info("Server listening on port: " + serverSocket.getLocalPort());

        while (running){
            try {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket,this);
                clients.add(clientHandler);
                clientHandler.start();
                log.info("Client connected!");

            } catch (IOException e) {
                log.info("Server terminated!");
            }
        }
        stopAllThreads();
    }

    private void stopAllThreads() {
        for (ClientHandler client : clients) {
            try {
                client.getSocket().close();

            } catch (IOException ex) {
                log.warning("Client thread failed to stop: " + ex.getMessage());
            }
        }
    }

    public void terminate() {
        try {
            if(!clients.isEmpty()){//before terminating server notify clients
                Response response = new Response();
                response.setStatus(ResponseStatus.TERMINATED);
                for (ClientHandler client : clients) {
                    client.getToClient().writeObject(response);
                    if(client.getLoggedUser() != null)
                        log.info(String.format("Terminating client program for: %s", client.getLoggedUser().getEmail()));
                    client.getSocket().close();
                }
            }
            running = false;
            serverSocket.close();

        } catch (IOException e) {
            log.warning("Failed to terminate server" + e.getMessage());
        }
    }

}

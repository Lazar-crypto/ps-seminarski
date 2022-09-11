package server;


import dto.impl.UserDTO;
import lombok.extern.java.Log;
import network.Request;
import network.Response;
import network.ResponseStatus;
import operation.Operation;
import operation.concrete.Login;
import ui.controller.ServerController;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

 @Log
public class ClientHandler extends Thread {

    private final Socket socket;

    private final ObjectOutputStream toClient;

    private final ObjectInputStream fromClient;

    private final Server server;

    private UserDTO loggedUser;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        toClient = new ObjectOutputStream(socket.getOutputStream());
        fromClient = new ObjectInputStream(socket.getInputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getToClient(){
        return toClient;
    }

    public UserDTO getLoggedUser(){
        return loggedUser;
    }

    public void setLoggedUser(UserDTO loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public void run() {
        while (true){
            try {
                Request request = (Request) fromClient.readObject();
                Response response = handleRequest(request);
                toClient.writeObject(response);
                toClient.flush();

            } catch (EOFException | SocketException e) { //means user logged out(closed session)
                ServerController.getInstance().removeUser(loggedUser);
                server.getClients().remove(this);
                if(loggedUser != null)
                    log.info(String.format("USER: %s logged out!",loggedUser.getEmail()));
                break;

            }catch (IOException | ClassNotFoundException e) {
                log.severe("Failed reading request or sending response data from/to client: " + e.getMessage());
                break;
            }
        }
    }

    private Response handleRequest(Request request) {
        Operation operation;
        switch (request.getRequestType()){
            case LOGIN:
                operation = new Login(this);
                break;

            default:
                return new Response(null, ResponseStatus.ERROR, new Exception("Korisnicki zahtev ne postoji!"));
        }

        operation.setReceivedData(request.getData());
        return operation.execute();
    }
}

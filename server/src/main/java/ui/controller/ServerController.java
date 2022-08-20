package ui.controller;

import dto.impl.UserDTO;
import lombok.extern.java.Log;
import server.Server;
import ui.table.model.UsersTableModel;
import ui.view.ServerForm;

import java.io.IOException;

@Log
public class ServerController {

    private static ServerController instance;
    
    private final ServerForm serverForm;
    
    private Server server;
    
     private ServerController() {
        serverForm = new ServerForm();
        setListeners();
    }

    public static ServerController getInstance() {
        if (instance == null) instance = new ServerController();
        return instance;
    }
    
    public void show(){
        serverForm.setVisible(true);
    }

    private void setListeners() {
        serverForm.getBtnStartServer().addActionListener(a -> startServer());
        serverForm.getBtnStopServer().addActionListener(a -> stopServer());
    }

    private void startServer() {
         log.info("Server STARTED");
         try {
            server = new Server();
        } catch (IOException ex) {
             log.info("Cannot instantiate Server");
            serverForm.printError("Greska prilikom pokretanja servera!");
        }
         server.start();
         serverForm.serverStarted();
    }

    private void stopServer() {
         if(!server.getClients().isEmpty()){
             serverForm.printError("Postoje aktivne sesije!");
             confirmTerminateServer();
         }else if (((UsersTableModel)serverForm.getTblActiveUsers().getModel()).getActiveUsers().size() > 0){
            serverForm.printError("Postoje aktivni korisnici!");
             confirmTerminateServer();
        }
    }

    private void confirmTerminateServer(){
        if(serverForm.confirmDialog("Da li ste sigurni da zelite da ugasite server?")){
            server.terminate();
            serverForm.serverStopped();
            log.info("server STOPPED");
        }
    }
    
    public void addUser(UserDTO user){
        ((UsersTableModel)serverForm.getTblActiveUsers().getModel()).addUser(user);
    }

    public void removeUser(UserDTO user){
        ((UsersTableModel)serverForm.getTblActiveUsers().getModel()).removeUser(user);
    }
    
}

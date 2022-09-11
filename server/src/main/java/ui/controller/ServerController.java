package ui.controller;

import dto.impl.UserDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import lombok.extern.java.Log;
import server.Server;
import ui.table.model.UsersTableModel;
import ui.view.ServerForm;

import javax.swing.*;
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
        serverForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               if(!shouldServerBeTerminated())
                   serverForm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
               else{
                   serverForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                   if(server != null)
                       server.terminate();
                   log.info("server STOPPED");
               }
            }
        });
    }

    private void startServer() {
         log.info("Server STARTED");
         try {
            server = new Server();
        } catch (IOException ex) {
             log.info("Cannot instantiate Server");
            serverForm.errorDialog("Greska prilikom pokretanja servera!","Greska");
        }
         server.start();
         serverForm.serverStarted();
    }

    private void stopServer() {
        if(!shouldServerBeTerminated())
            return;
        server.terminate();
        serverForm.serverStopped();
        log.info("server STOPPED");
    }

    private boolean shouldServerBeTerminated(){
        if(server != null && !server.getClients().isEmpty()){
             serverForm.warningDialog("Postoje aktivne sesije","OPREZ");
             return serverForm.confirmDialog("Da li ste sigurni da zelite da ugasite server?", "OPREZ");
             }
        return true;
    }
    
    public void addUser(UserDTO user){
        ((UsersTableModel)serverForm.getTblActiveUsers().getModel()).addUser(user);
    }

    public void removeUser(UserDTO user){
        ((UsersTableModel)serverForm.getTblActiveUsers().getModel()).removeUser(user);
    }
    
}

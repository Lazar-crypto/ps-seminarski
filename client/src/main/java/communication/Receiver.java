package communication;

import exception.ResponseNotReceivedException;
import lombok.extern.java.Log;
import network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

@Log
public class Receiver {

    private final ObjectInputStream fromServer;


    public Receiver(Socket socket) throws IOException {
        fromServer = new ObjectInputStream(socket.getInputStream());
    }

    public Response receiveRespone(){
        try {
            return (Response) fromServer.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new ResponseNotReceivedException("Nije stigao odgovor od servera!");
        }
    }

}

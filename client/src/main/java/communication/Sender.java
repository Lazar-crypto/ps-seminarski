package communication;

import lombok.extern.java.Log;
import network.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Log
public class Sender {

    private final ObjectOutputStream toServer;

    public Sender(Socket socket) throws IOException {
        toServer = new ObjectOutputStream(socket.getOutputStream());
    }

    public void sendRequest(Request request){
        try {
            toServer.writeObject(request);
            toServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
            log.info("Error while sending request to server");
        }
    }
}

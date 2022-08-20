package communication;

import exception.ServerUnavailableException;
import lombok.extern.java.Log;
import network.Request;
import network.RequestType;
import network.Response;
import network.ResponseStatus;
import properties.TransferProperties;

import javax.swing.*;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

@Log
public class Session {

    protected Sender sender;

    protected Receiver receiver;

    private static Session instance;

    private Session(){
        try {
            Socket socket = new Socket(TransferProperties.getInstance().getProperty("server_name"),
                    Integer.parseInt(TransferProperties.getInstance().getProperty("server_port")));
            sender = new Sender(socket);
            receiver = new Receiver(socket);
            log.info("Sender and Receiver initialized");

        }catch(ConnectException ce){
            throw new ServerUnavailableException("Server nedostupan, proverite da li je pokrenut.");
        } catch (IOException e) {
            log.info("Errow while trying to connect");
        }
    }

    public static Session getInstance(){
        if(instance == null)
            instance = new Session();
        return instance;
    }

    public Object makeRequestReceiveResponse(Object data, RequestType type) throws Exception{
        Request request = new Request(data,type);
        sender.sendRequest(request);
        log.info("Request sent: " + request);

        Response response = receiver.receiveRespone();
        log.info("Response received: " + response);
        if(response.getStatus().equals(ResponseStatus.ERROR)) throw response.getError();
        return response.getData();
    }
}

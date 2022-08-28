package communication;

import exception.ResponseNotReceivedException;
import network.Response;
import network.ResponseStatus;
import view.dialog.DialogForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver implements Runnable{

    private final ObjectInputStream fromServer;

    private Response response;

    public Receiver(Socket socket) throws IOException {
        fromServer  = new ObjectInputStream(socket.getInputStream());
        new Thread(this).start();
    }

    public synchronized Response getResponse() throws InterruptedException {
        if(response == null)
            this.wait(); // wait for server to send response

        return response;
    }

    public void setResponse(Response response){
        this.response = response;
    }

    @Override
    public void run() {
        while(true){
            response = receiveResponse();

            synchronized(this){
                setResponse(response);
                this.notify();
            }

            if(response.getStatus().equals(ResponseStatus.TERMINATED)){
                DialogForm.showErrorDialog(null,"SERVER UGASEN!","GRESKA");
                System.exit(1);
            }
        }
    }

    public Response receiveResponse(){
        try {
           return (Response) fromServer.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new ResponseNotReceivedException("Nije stigao odgovor od servera!");
        }
    }

}

package operation;

import lombok.extern.java.Log;
import network.Response;
import network.ResponseStatus;
import repository.Repository;

import java.sql.SQLException;

@Log
public abstract class Operation {

    protected Object receivedData;
    protected Repository repository;

    public void setReceivedData(Object receivedData) {
        this.receivedData = receivedData;
    }

    //template method for every system operation
    public Response execute(){
        try {
            adjustReceivedData();
            checkConstraints();
            Object responseData = concreteOperationResponse();
            commitTransaction();
            return new Response(responseData, ResponseStatus.SUCCESS);

        }catch (Exception e) {
            log.info("Operation failed");
            log.info("Rolling back transaction.");
            try{
                rollbackTransaction();

            } catch (SQLException ex) {
                log.severe("Failed to roll back transaction");
                return new Response(null,ResponseStatus.ERROR, ex);
            }
            return new Response(null,ResponseStatus.ERROR, e);
        }
    }

    protected abstract void adjustReceivedData();
    protected abstract Object concreteOperationResponse() throws Exception;
    protected abstract void checkConstraints() throws Exception;

    private void rollbackTransaction() throws SQLException{
        repository.rollbackTransaction();
    }

    private void commitTransaction() throws SQLException{
        repository.commitTransaction();
    }


}

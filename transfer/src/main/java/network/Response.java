package network;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Response implements Serializable {
    
    private Object data;
    private ResponseStatus status;
    private Exception error;
   
    public Response(Object data, ResponseStatus status) {
        this.data = data;
        this.status = status;
    }
}

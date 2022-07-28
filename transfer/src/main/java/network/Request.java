package network;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Request implements Serializable {
   
    private  Object data;
    private  RequestType requestType;

}
package controller;

import communication.Session;
import dto.impl.UserDTO;
import dto.impl.UserLoginDTO;
import network.RequestType;

public class UserController {

    private static UserController instance;

    public static UserController getInstance(){
        if(instance == null)
            instance = new UserController();
        return instance;
    }

    public UserDTO login(String username,String password) throws Exception {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username,password);
        return (UserDTO) Session.getInstance().makeRequestReceiveResponse(userLoginDTO, RequestType.LOGIN);
    }

}

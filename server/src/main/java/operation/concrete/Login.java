package operation.concrete;

import dao.DAO;
import dao.factory.DaoFactory;
import dto.impl.UserDTO;
import dto.impl.UserLoginDTO;
import entity.Entity;
import entity.implementation.User;
import exception.UserNotFoundException;
import lombok.extern.java.Log;
import mapper.UserDTOMapper;
import operation.Operation;
import repository.Repository;
import server.ClientHandler;
import ui.controller.ServerController;

import java.util.List;
import java.util.Objects;

@Log
public class Login extends Operation {

    private String email;
    private String password;

    private final ClientHandler clientHandler;

    public Login(ClientHandler client){
        this.clientHandler = client;
    }

    @Override
    protected void adjustReceivedData() {
        UserLoginDTO userLoginDTO = (UserLoginDTO) super.receivedData;
        email = userLoginDTO.getEmail();
        password = userLoginDTO.getPassword();
    }

    @Override
    protected UserDTO concreteOperationResponse() throws Exception {
        if(super.repository == null)
            super.repository = new Repository();

        User user = (User) super.repository
                .findByCondition(String.format("email = '%s' and password = '%s'", email, password),
                        Objects.requireNonNull(DaoFactory.create(User.class)))
                .stream()
                .findFirst()
                .orElseThrow(() ->  new UserNotFoundException("Ne postoji korisnik!"));

       UserDTO userDto = UserDTOMapper.fromEntity(user);
       
        //dodaj usera u listu aktivnih
        ServerController.getInstance().addUser(userDto);
        clientHandler.setLoggedUser(userDto);

        log.info("User logged in successfully");
        return userDto;
    }

    @Override
    protected void checkConstraints() throws Exception {
        //nothing to check
    }
}

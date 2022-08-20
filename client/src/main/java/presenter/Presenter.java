package presenter;

import controller.UserController;
import dto.impl.UserDTO;

import java.awt.*;

public abstract class Presenter {

    protected final UserController userController;

    protected final UserDTO user;

    protected  Window form;

    protected Presenter(UserDTO user) {
        this.user = user;
        userController = UserController.getInstance();
    }

    public final void show(){
        form.setVisible(true);
    }

    protected abstract void setListeners();

    protected abstract void prepareForm();

}

package presenter.concrete;

import controller.UserController;
import dto.impl.UserDTO;
import exception.ServerUnavailableException;
import presenter.Presenter;
import view.form.LoginForm;
import view.form.MainForm;

import javax.swing.*;

public class MainPresenter extends Presenter {


    public MainPresenter() {
        super(null);
        super.form = new LoginForm(null,true);
        LoginForm loginForm = (LoginForm) form;
        loginForm.getBtnLogin().addActionListener(actionEvent -> loginUser(loginForm));
    }

    private void loginUser(LoginForm loginForm) {
        try{
            //napraviti validaciju
            String username = loginForm.getTxtUsername().getText();
            String password = String.valueOf(loginForm.getTxtPassword().getPassword());
            if(username.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(loginForm,"Podaci ne smeju biti prazni!");
                return;
            }

            UserDTO user = UserController.getInstance().login(loginForm.getTxtUsername().getText(),
                    new String(loginForm.getTxtPassword().getPassword()));
            loginForm.dispose();
            JOptionPane.showMessageDialog(loginForm,"Uspesno ste se prijavili,","Prijava",JOptionPane.INFORMATION_MESSAGE);
            super.form = new MainForm();

        }catch (ServerUnavailableException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska",JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex){
            System.out.println("Nesto");
            JOptionPane.showMessageDialog(loginForm, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void prepareForm() {

    }
}

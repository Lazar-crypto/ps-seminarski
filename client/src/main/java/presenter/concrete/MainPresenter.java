package presenter.concrete;

import controller.UserController;
import dto.impl.UserDTO;
import exception.ServerUnavailableException;
import presenter.Presenter;
import view.dialog.DialogForm;
import view.form.LoginForm;
import view.form.MainForm;

public class MainPresenter extends Presenter {


    public MainPresenter() {
        super(null);
        super.form = new LoginForm(null,true);
        LoginForm loginForm = (LoginForm) form;
        loginForm.getBtnLogin().addActionListener(actionEvent -> loginUser(loginForm));
    }

    private void loginUser(LoginForm loginForm) {
        try{
            //TODO napraviti validaciju
            String username = loginForm.getTxtUsername().getText();
            String password = String.valueOf(loginForm.getTxtPassword().getPassword());
            if(username.isEmpty() || password.isEmpty()){
                DialogForm.showInformationDialog(loginForm,"Podaci ne smeju biti prazni!","Probajte opet");
                return;
            }
            UserDTO user = UserController.getInstance().login(username, password);
            System.out.println(user.getName());
            loginForm.dispose();
            DialogForm.showInformationDialog(loginForm,"Uspesno ste se prijavili.","Prijava");
            super.form = new MainForm(user);
            super.show();

        }catch (ServerUnavailableException ex){
            DialogForm.showErrorDialog(null, ex.getMessage(),"Greska");

        } catch (Exception ex){
            ex.printStackTrace();
            DialogForm.showErrorDialog(loginForm, ex.getMessage(),"Greska");
        }

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void prepareForm() {

    }
}

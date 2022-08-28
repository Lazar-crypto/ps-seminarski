package view.dialog;

import javax.swing.*;
import java.awt.*;

public class DialogForm extends JOptionPane {

    public static void showErrorDialog(Component component,String msg, String title){
        JOptionPane.showMessageDialog(component, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    public static boolean showConfirmDialog(Component component, String msg, String title){
        String[] options = {"Da", "Ne"};
        int result = JOptionPane.showOptionDialog(
                component,
                msg,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]
        );
        return result == JOptionPane.YES_OPTION;
    }

    public static void showWarningDialog(Component component, String msg, String title){
        JOptionPane.showMessageDialog(component,msg,title,JOptionPane.WARNING_MESSAGE);
    }

    public static void showInformationDialog(Component component, String msg, String title){
        JOptionPane.showMessageDialog(component, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

}

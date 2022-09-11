package ui.view;

import properties.TransferProperties;
import ui.table.model.UsersTableModel;
import view.dialog.DialogForm;

import javax.swing.*;
import java.awt.*;

public class ServerForm extends JFrame {

    public ServerForm() {
        initComponents();
        prepareForm();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPort = new javax.swing.JFormattedTextField();
        btnStartServer = new javax.swing.JButton();
        btnStopServer = new javax.swing.JButton();
        lblServerStatus = new javax.swing.JLabel();
        pnlCurrentUsers = new javax.swing.JPanel();
        scrlUsers = new javax.swing.JScrollPane();
        tblActiveUsers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Podešavanja servera");

        jLabel1.setText("Port:");

        txtPort.setEditable(false);
        txtPort.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("####"))));
        txtPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnStartServer.setText("Pokreni server");

        btnStopServer.setText("Zaustavi server");

        lblServerStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServerStatus.setText("Server nije pokrenut");

        pnlCurrentUsers.setBorder(javax.swing.BorderFactory.createTitledBorder("Aktivni korisnici"));

        scrlUsers.setViewportView(tblActiveUsers);

        javax.swing.GroupLayout pnlCurrentUsersLayout = new javax.swing.GroupLayout(pnlCurrentUsers);
        pnlCurrentUsers.setLayout(pnlCurrentUsersLayout);
        pnlCurrentUsersLayout.setHorizontalGroup(
            pnlCurrentUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlUsers)
        );
        pnlCurrentUsersLayout.setVerticalGroup(
            pnlCurrentUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCurrentUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblServerStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnStartServer, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnStopServer, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartServer)
                    .addComponent(btnStopServer))
                .addGap(18, 18, 18)
                .addComponent(lblServerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlCurrentUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnStartServer() {
        return btnStartServer;
    }

    public JButton getBtnStopServer() {
        return btnStopServer;
    }

    public JLabel getLblServerStatus() {
        return lblServerStatus;
    }

    public JPanel getPnlCurrentUsers() {
        return pnlCurrentUsers;
    }

    public JTable getTblActiveUsers() {
        return tblActiveUsers;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartServer;
    private javax.swing.JButton btnStopServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblServerStatus;
    private javax.swing.JPanel pnlCurrentUsers;
    private javax.swing.JScrollPane scrlUsers;
    private javax.swing.JTable tblActiveUsers;
    private javax.swing.JFormattedTextField txtPort;
    // End of variables declaration//GEN-END:variables

    private void prepareForm() {
        tblActiveUsers.setModel(new UsersTableModel());
        pnlCurrentUsers.setVisible(false);
        txtPort.setValue(Integer.parseInt(TransferProperties.getInstance().getProperty("server_port")));
        btnStopServer.setEnabled(false);
    }

    public void errorDialog(String msg, String title) {
        DialogForm.showErrorDialog(this, msg, title);
    }

    public void warningDialog(String msg, String title){
        DialogForm.showWarningDialog(this, msg, title);
    }
    
    public boolean confirmDialog(String msg, String title){
         return DialogForm.showConfirmDialog(this,msg,title);
    }

    public void serverStopped() {
        pnlCurrentUsers.setVisible(false);
        btnStopServer.setEnabled(false);
        btnStartServer.setEnabled(true);
        lblServerStatus.setText("Server je zaustavljen.");
        lblServerStatus.setForeground(Color.RED);

    }

    public void serverStarted() {
        pnlCurrentUsers.setVisible(true);
        tblActiveUsers.setModel(new UsersTableModel());
        btnStartServer.setEnabled(false);
        btnStopServer.setEnabled(true);
        lblServerStatus.setText("Server je pokrenut.");
        lblServerStatus.setForeground(Color.BLUE);
    }
}

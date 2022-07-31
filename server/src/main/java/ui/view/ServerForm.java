package ui.view;

import properties.TransferProperties;
import ui.table.model.UsersTableModel;

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

        jLabel1 = new JLabel();
        txtPort = new JFormattedTextField();
        btnStartServer = new JButton();
        btnStopServer = new JButton();
        lblServerStatus = new JLabel();
        pnlCurrentUsers = new JPanel();
        scrlUsers = new JScrollPane();
        tblActiveUsers = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Podešavanja servera");

        jLabel1.setText("Port:");

        txtPort.setEditable(false);
        txtPort.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("####"))));
        txtPort.setHorizontalAlignment(JTextField.CENTER);

        btnStartServer.setText("Pokreni server");

        btnStopServer.setText("Zaustavi server");

        lblServerStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblServerStatus.setText("Server nije pokrenut");

        pnlCurrentUsers.setBorder(BorderFactory.createTitledBorder("Aktivni korisnici"));

        scrlUsers.setViewportView(tblActiveUsers);

        GroupLayout pnlCurrentUsersLayout = new GroupLayout(pnlCurrentUsers);
        pnlCurrentUsers.setLayout(pnlCurrentUsersLayout);
        pnlCurrentUsersLayout.setHorizontalGroup(
            pnlCurrentUsersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrlUsers)
        );
        pnlCurrentUsersLayout.setVerticalGroup(
            pnlCurrentUsersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrlUsers, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCurrentUsers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblServerStatus, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnStartServer, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnStopServer, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPort, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartServer)
                    .addComponent(btnStopServer))
                .addGap(18, 18, 18)
                .addComponent(lblServerStatus, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlCurrentUsers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private JButton btnStartServer;
    private JButton btnStopServer;
    private JLabel jLabel1;
    private JLabel lblServerStatus;
    private JPanel pnlCurrentUsers;
    private JScrollPane scrlUsers;
    private JTable tblActiveUsers;
    private JFormattedTextField txtPort;
    // End of variables declaration//GEN-END:variables

    private void prepareForm() {
        tblActiveUsers.setModel(new UsersTableModel());
        pnlCurrentUsers.setVisible(false);
        txtPort.setValue(Integer.parseInt(TransferProperties.getInstance().getProperty("server_port")));
        btnStopServer.setEnabled(false);
    }

    public void printError(String s) {
        JOptionPane.showMessageDialog(this, s, "Greška", JOptionPane.ERROR_MESSAGE);
    }
    
    public boolean confirmDialog(String msg){
         return JOptionPane.showConfirmDialog(this, msg, "OPREZ", 
                 JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
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

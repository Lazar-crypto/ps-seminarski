package ui.table.model;

import dto.impl.UserDTO;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UsersTableModel extends AbstractTableModel{
    
    private final String[] columnNames = new String[]{"Ime", "Prezime", "Broj advokatske legitimacije","Email"};
    
    private List<UserDTO> activeUsers = new ArrayList<>();
    
    public void setActiveUsers(List<UserDTO> activeUsers) {
        this.activeUsers = activeUsers;
    }

    public List<UserDTO> getActiveUsers() {
        return activeUsers;
    }

    public void addUser(UserDTO user) {
        activeUsers.add(user);
        fireTableDataChanged();
    }

    public void removeUser(UserDTO user) {
        activeUsers.remove(user);
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public int getRowCount() {
        return activeUsers.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UserDTO u = activeUsers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getName();
            case 1:
                return u.getSurname();
            case 2:
                return u.getAttorneyIdentificationNumber();
            case 3:
                return u.getEmail();
            default:
                return "N/A";
        }
    }
    
}

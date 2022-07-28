package entity.implementation;

import entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class User implements Entity{
    
    private int id;
    private int attorneyIdentificationNumber;
    private String name;
    private String surname;
    private String email;
    private String password;

}

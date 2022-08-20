package dto.impl;

import dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserDTO implements DTO {

    private final long id;
    private long attorneyIdentificationNumber;
    private final String name;
    private final String surname;
    private final String email;

}

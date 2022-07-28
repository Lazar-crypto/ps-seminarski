package dto.impl;

import dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserLoginDTO implements DTO {

    private final String username;
    private final String password;
}

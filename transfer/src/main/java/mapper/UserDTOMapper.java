package mapper;

import dto.impl.UserDTO;
import entity.implementation.User;

public final class UserDTOMapper {

    public static UserDTO fromEntity(User user){
        return new UserDTO(user.getId(), user.getAttorneyIdentificationNumber(), user.getName(), user.getSurname(), user.getEmail());
    }
}

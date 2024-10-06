package vn.tiendat.service.impl;

import org.springframework.stereotype.Service;
import vn.tiendat.dto.request.UserRequestDTO;
import vn.tiendat.exception.ResourceNotFoundException;
import vn.tiendat.service.UserSevice;

@Service
public class UserServiceImpl implements UserSevice {

    @Override
    public int addUser(UserRequestDTO requestDTO) {
        System.out.println("Save user to db");
        if(!requestDTO.getFirstName().equals("Tay")){
            throw new ResourceNotFoundException("Tay ko ton tai");
        }
        return 0;
    }
}

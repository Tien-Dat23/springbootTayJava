package vn.tiendat.service;

import vn.tiendat.dto.request.UserRequestDTO;

public interface UserSevice {
    int addUser(UserRequestDTO requestDTO);
}

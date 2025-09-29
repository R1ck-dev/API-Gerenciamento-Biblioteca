package com.henrique.gerenciamento_biblioteca_api.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.UserDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;

public interface UserService {

    UserModel createUser(UserDTO userDTO);
    
} 
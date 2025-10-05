package com.henrique.gerenciamento_biblioteca_api.Service;

import java.util.List;

import com.henrique.gerenciamento_biblioteca_api.DTO.UserDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.UpdateRequestUserDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Summary.UserSummaryDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;

public interface UserService {

    UserModel createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UpdateRequestUserDTO updateDTO);

    void deleteUser(Long id);

    List<UserSummaryDTO> getUsers();

} 
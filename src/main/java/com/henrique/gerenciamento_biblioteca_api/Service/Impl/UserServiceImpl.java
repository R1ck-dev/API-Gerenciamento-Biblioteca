package com.henrique.gerenciamento_biblioteca_api.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.UserDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.UpdateRequestUserDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Summary.UserSummaryDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;
import com.henrique.gerenciamento_biblioteca_api.Repository.UserRepository;
import com.henrique.gerenciamento_biblioteca_api.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserModel createUser(UserDTO userDTO) {
        UserModel userModel = new UserModel();

        userModel.setName(userDTO.getName());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userModel.setRole(userDTO.getRole());

        userRepository.save(userModel);
        return userModel;
    }

    @Override
    public UserDTO updateUser(Long id, UpdateRequestUserDTO updateDTO) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário Não Encontrado!"));

        if (updateDTO.getName() != null && !updateDTO.getName().isEmpty()) {
        user.setName(updateDTO.getName());
        }

        if (updateDTO.getEmail() != null && !updateDTO.getEmail().isEmpty()) {
            user.setEmail(updateDTO.getEmail());    
        }

        UserModel savedUser = userRepository.save(user);

        return convertToUserDTO(savedUser);
    }

    public UserDTO convertToUserDTO(UserModel userModel) {
        UserDTO userDTO = new UserDTO();

        userDTO.setName(userModel.getName());
        userDTO.setEmail(userModel.getEmail());

        return userDTO;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserSummaryDTO> getUsers() {
        List<UserModel> userList = userRepository.findAll();

        return userList.stream()
                .map(this::convertToUserSummaryDTO)
                .collect(Collectors.toList());
    }

    public UserSummaryDTO convertToUserSummaryDTO(UserModel userModel) {
        UserSummaryDTO userSummaryDTO = new UserSummaryDTO();

        userSummaryDTO.setId(userModel.getId());
        userSummaryDTO.setEmail(userModel.getEmail());
        userSummaryDTO.setName(userModel.getName());
        userSummaryDTO.setRole(userModel.getRole());

        return userSummaryDTO;
    }
}

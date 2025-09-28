package com.henrique.gerenciamento_biblioteca_api.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.UserDTO;
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
    
}

package com.henrique.gerenciamento_biblioteca_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    UserDetails findByEmail(String email);
} 
